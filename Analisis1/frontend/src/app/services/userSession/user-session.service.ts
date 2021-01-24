import { Injectable, Output, EventEmitter } from '@angular/core';
import { UserComplete } from 'src/app/models/user';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {api_url} from '../../../Global_var'
import { StoreSimple } from 'src/app/models/store';
@Injectable({
  providedIn: 'root'
})
export class UserSessionService {

  constructor(private router: Router,private http: HttpClient) { }

  isLoggedService = false;
  loggedName = ""
  userId = 0
  userRol = 0


  @Output() change: EventEmitter<boolean> = new EventEmitter();
  @Output() changeName: EventEmitter<String> = new EventEmitter();
  @Output() changeId: EventEmitter<number> = new EventEmitter();
  @Output() changeRol: EventEmitter<number> = new EventEmitter();
  saveUser(user_complete: UserComplete){
    localStorage.setItem('userEmail', user_complete.correo || "");
    localStorage.setItem('userId', user_complete.id?.toString() || "");
    localStorage.setItem('userIdRol', user_complete.idRol?.toString() || "");
    localStorage.setItem('userEstado', user_complete.estado?.toString() || "");
    localStorage.setItem('userFirstName', user_complete.nombre || "");
    localStorage.setItem('userLastName', user_complete.apellido || "");
    localStorage.setItem('userPhone', user_complete.telefono?.toString() || "");
  }
  historial(userId: number) {
    return this.http.post(`${api_url}/order/usuario`,{id: userId});
  }
  login(user_complete: UserComplete) {
    this.saveUser(user_complete);
    this.isLoggedService = true;
    this.loggedName = user_complete.nombre || ""
    this.change.emit(this.isLoggedService)
    this.changeName.emit(this.loggedName)

    this.userId = user_complete.id || 0
    this.changeId.emit(this.userId)
    console.log("WW",user_complete.idRol)
    this.userRol = user_complete.idRol || 0
    this.changeRol.emit(this.userRol)

  }
  saveStore(storeM : StoreSimple){
    localStorage.setItem('idTienda', storeM.id?.toString() || "0");
    localStorage.setItem('nombreTienda', storeM.nombre || "");
    localStorage.setItem('sloganTienda', storeM.slogan || "");
    localStorage.setItem('estadoTienda', storeM.estado?.toString() || "0");
    localStorage.setItem('imagenTienda', storeM.imagen || "");
  }
  getStoreUserId(userId : number){
    return this.http.post(`${api_url}/store/info`,{id: userId});
  }

  check() {
    let data = localStorage.getItem('userEmail');
    let usrName = localStorage.getItem('userFirstName');
    this.loggedName = usrName || "noUser"
    this.changeName.emit(this.loggedName)


    if (data != "") {
      this.isLoggedService = true;
      this.change.emit(this.isLoggedService)

      this.userId = parseInt(localStorage.getItem('userId') || "0") 
      this.changeId.emit(this.userId)

      this.userRol = parseInt(localStorage.getItem('userIdRol') || "0")
      this.changeRol.emit(this.userRol)
    }
    else {
      this.isLoggedService = false;
      this.change.emit(this.isLoggedService)
    
      this.userId = 0
      this.changeId.emit(this.userId)

      this.userRol =  0
      this.changeRol.emit(this.userRol)
    }
  }
  /*    Valores idRol
     1: Comprobar Admin     2: Comprobar CLiente
     3: Comprobar Admin Tienda    4: Redirigir A Panel 
     5: Comprobar AdminTienda o CLiente*/
  userHref(idRol: number) {
    let data = localStorage.getItem('userIdRol');
    let usrName = localStorage.getItem('userFirstName');
    this.loggedName = usrName || "Q"
    this.changeName.emit(this.loggedName)

    if (data == "")
      this.router.navigate(['login']);
    let idStorage = parseInt(data || "0")
    if (idRol == idStorage && idRol != 4)
      return;

    if(idRol == 5 && (idStorage == 2 || idStorage == 3))
      return

    switch (idStorage) {
      case 1:
        this.router.navigate(['admin/profile']);
        break;

      case 2:
        this.router.navigate(['client/profile']);
        break;

      case 3:
        this.router.navigate(['merchant']);
        break;

      default:
        this.router.navigate(['login']);
        break;
    }
  }


  logout() {
    //Usuario
    localStorage.setItem('userEmail', "");
    localStorage.setItem('userId', "");
    localStorage.setItem('userIdRol', "");
    localStorage.setItem('userFirstName', "");
    localStorage.setItem('userLastName', "");
    localStorage.setItem('userPhone', "");
    localStorage.setItem('userEstado', "");
    localStorage.removeItem('carrito')
    //Tienda
    localStorage.setItem('idTienda', "");
    localStorage.setItem('nombreTienda',  "");
    localStorage.setItem('sloganTienda',  "");
    localStorage.setItem('estadoTienda',  "");
    localStorage.setItem('imagenTienda',  "");
    this.isLoggedService = false;
    this.change.emit(this.isLoggedService)
    this.userId = 0
    this.changeId.emit(this.userId)

    this.userRol =  0
    this.changeRol.emit(this.userRol)
  }
}
