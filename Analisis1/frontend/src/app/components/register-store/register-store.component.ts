import { Component, OnInit, HostBinding } from '@angular/core';
import { Store } from 'src/app/models/store';
import { Router } from '@angular/router';
import { StoreService } from 'src/app/services/storeService/store.service';

import { ToastrService } from 'ngx-toastr'; 
import { UserSessionService } from 'src/app/services/userSession/user-session.service';

@Component({
  selector: 'app-register-store',
  templateUrl: './register-store.component.html',
  styleUrls: ['./register-store.component.css']
})
export class RegisterStoreComponent implements OnInit {

  constructor(private tiendaService:StoreService,private router: Router,private toastr: ToastrService,
    private sessionService : UserSessionService) { }
  tienda:Store={
    idRol:3,
    nombre:"",
    nombreTienda:"",
    contrasenia: "",
    telefono: "",
    slogan:"",
    idUsuario:0,
    estado:0,
    imagen:"texto"
  }
  ngOnInit(): void {
    //this.sessionService.userHref(4)
    if (this.logged())
      this.router.navigate(['/']);
  }

  logged() : boolean {
    let data = localStorage.getItem('userIdRol');
    if (data != "")
      return true
    else
      return false
  }

  registrarTienda():void{
    console.log(this.tienda);
    let constraint:boolean = this.tienda.nombre == "" || this.tienda.nombreTienda == "" || this.tienda.contrasenia == ""
    let cons = constraint ||  this.tienda.apellido == "" ||  this.tienda.correo == ""
    if(cons){
      this.toastr.error("Campos Vacios",'Error')
      return;
    }
    if(this.tienda.confirmarContrasenia != this.tienda.contrasenia){
      this.toastr.error("Los Contrasenias No Son Iguales",'Error')
      return;
    }
    this.tiendaService.createStore(this.tienda)
      .subscribe(
        res => {
          this.toastr.info("Tienda Creada Exitosamente", 'Aviso');  
          
        },
        err => {
          this.toastr.error("No Se Pudo Crear Tienda, Revise Los Campos",'Error')
        }
      )
  }

  yaExiste():void{
    this.sessionService.userHref(4)
    
        
  }
}
