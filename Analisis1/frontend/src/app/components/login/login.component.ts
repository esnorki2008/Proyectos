import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import { StoreSimple } from 'src/app/models/store';
import { UserSessionService } from 'src/app/services/userSession/user-session.service';

import { User, UserComplete } from '../../models/user';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public email: string = "";
  public password: string = "";
  user: User = {
    correo: "",
    contrasenia:""
  }

  user_complete: UserComplete = {
    nombre: "",
    apellido: "",
    correo: "",
    contrasenia: "",
    telefono: 0,
    idRol: 0,
    estado: -1
  }

  store_inf: StoreSimple ={
    id: 0,
    nombreTienda: "",
    slogan: "",
    idUsuario: 0,
    estado: 0,
    imagen: ""
  }
  constructor(private userService: DataService, private router: Router, private toastr: ToastrService,
      private userSession: UserSessionService) { }

  ngOnInit(): void {
    this.userSession.userHref(4)
  }

  verficarDatos() { 
    if(this.email != "" && this.password != ""){
      this.user.correo = this.email;
      this.user.contrasenia = this.password;
      this.userService.postLogin(this.user)
      .subscribe(
        (res) => {
          if(res != undefined){
            this.user_complete = res;

            this.userSession.login(this.user_complete);
            

            if(this.user_complete.estado == 1){
              if(this.user_complete.idRol == 1){
                this.toastr.success("Ingreso exitoso", "Bienvenido " + this.user_complete.nombre)
                this.router.navigate(['/admin/profile']);
              }
              else if (this.user_complete.idRol == 3){
                this.toastr.success("Ingreso exitoso", "Bienvenido " + this.user_complete.nombre)
               


                this.userSession.getStoreUserId(this.user_complete.id || 0).subscribe(
                  res=>{
                    this.store_inf = res
                    this.userSession.saveStore(this.store_inf)
                    this.router.navigate(['/merchant']);
                  },err=>{
                    console.log(err)
                    this.toastr.error(" Error", "No Se Pudo Obtener Informacion De La Tienda ")
                  })


              }
              else if (this.user_complete.idRol == 2){
                this.toastr.success("Ingreso exitoso", "Bienvenido " + this.user_complete.nombre)
                this.router.navigate(['/']);
              }
            }
            else {
              this.toastr.error("Estado inactivo", "Su cuenta esta inactiva en este momento");
            }
          }
          else {
            this.toastr.error("Error","Credenciales incorrectas");
          }
          
          return;
        },
        err => {
          this.toastr.error("Error", err.error.msg);
        }
        )

    }
    else{
      if(this.email == "" && this.password == ""){
        this.toastr.warning("Correo electrónico y contraseña faltante", "Ingrese todos los campos solicitados");
      }
      else if (this.email == "")
      {
        this.toastr.warning("Correo electrónico faltante", "Ingrese todos los campos solicitados");
      }
      else {
        this.toastr.warning("Contraseña faltante", "Ingrese todos los campos solicitados");
      }
      
    }
    //console.log(this.email);
    //console.log(this.password);
  }
}
