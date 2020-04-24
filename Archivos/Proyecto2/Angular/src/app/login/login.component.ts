import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  Ingresar():void{

  }
  Regresar():void{
    window.location.href = '/home';
  }
  Registrar():void{
    window.location.href = '/registrar';
  }
}
