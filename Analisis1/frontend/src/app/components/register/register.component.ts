import { Component, OnInit,HostBinding } from '@angular/core';
import {Router} from '@angular/router';
import {RegistroClienteService} from '../../services/registro_cliente/registro-cliente.service';
import {formulario_registro_cliente} from '../../models/formulario_registro_cliente';

import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  new_client: formulario_registro_cliente = {
    nombre: "",
    apellido : "",
    contrasenia: "",
    correo :"",
    telefono :0,
    idRol :2,
    estado:1
  };

  constructor(private registroClienteService:RegistroClienteService,private router:Router,private toastr:ToastrService ) {
    
  }



  ngOnInit(): void {

  }

  registrarse(){
      if(this.new_client.nombre == "" || this.new_client.apellido == "" || this.new_client.correo == "" || this.new_client.telefono==0){
          this.toastr.warning('Rellenar Todos Los Campos','Credenciales')
      }else{
      this.registroClienteService.RegistrarCliente(this.new_client).subscribe(
        res => {
            this.MensajeExito();
            this.Limpiar_campos();
            
        },
        err => {
          this.MensajeError('Cuenta Existente, Cambiar Correo');
          this.Limpiar_campos();
        }

      )

    }
  }


  Limpiar_campos(){
    this.new_client.correo="";
    this.new_client.apellido="";
    this.new_client.estado=0;
    this.new_client.idRol=2;
    this.new_client.nombre=""
    this.new_client.contrasenia="";
    this.new_client.telefono=0;
  }

  tohome(){
    this.router.navigate(['login']);
  }

  MensajeError(mensaje:any){
    this.toastr.error('Error',mensaje);
  }

  MensajeExito(){
    this.toastr.success('Registro','Se Ha Registrado Con Exito');
  }

}
