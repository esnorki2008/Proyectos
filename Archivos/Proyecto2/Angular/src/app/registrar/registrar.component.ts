import { Component, OnInit,ElementRef, ViewChild } from '@angular/core';
import {FormControl, FormGroupDirective, FormGroup, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})

export class RegistrarComponent implements OnInit {
  


  Nombre:string="";
  

  Formulario = new FormGroup({
    Apellido: new FormControl('', [
      Validators.required
    ]),
    Nombre: new FormControl('', [
      Validators.required,
      Validators.minLength(2)
    ]),
  });


  constructor() { }

  ngOnInit(): void {
  }

  
  Regresar():void{
    window.location.href = '/login';
  }
  Registrar():void{ 
    //this.NuevoUsuario.Apellido=this.Nombre
   console.log(this.NuevoUsuario);
  }

  NuevoUsuario: CrearUsuario=new CrearUsuario();
}

class CrearUsuario {
  Nombre: string;
  Apellido: string;
  Clave: string;
  Correo: string;
  Telefono: string;
  Genero:String;
  Fecha:String
  Direccion:String
}