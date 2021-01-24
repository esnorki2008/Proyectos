import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import * as globales from '../../../Global_var'


@Injectable({
  providedIn: 'root'
})
export class CatalogoServiceService {

  constructor(private http:HttpClient) { }
  
GetProductosTienda(Body:any){
  return this.http.post(`${globales.api_url}/product/listTienda`,Body);
}

ObtenerDepartamentosGeneral(){ // terminado
  return  this.http.get(`${globales.api_url}/department/listar`);
}

ObtenerTiendasGeneral(){ // terminado
  return  this.http.get(`${globales.api_url}/store/list`);
}

ObtenerTiendasDepartamento(Body:any){
  return this.http.post(`${globales.api_url}/store/filtrar`,Body);
}

// Parte de administracion de tiendas

ObtenerIDTienda(Body:any){ // por el momento no necesario esta en localstorage
  return this.http.post(`${globales.api_url}/store/info`,Body);
}







}

