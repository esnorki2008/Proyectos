import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {formulario_registro_cliente} from '../../models/formulario_registro_cliente';
import * as globales from '../../../Global_var'

@Injectable({
  providedIn: 'root'
})
export class RegistroClienteService {

  constructor(private http:HttpClient) { }

  RegistrarCliente(client:any){
    return this.http.post(`${globales.api_url}/user/clientRegister`,client);
  }




}



