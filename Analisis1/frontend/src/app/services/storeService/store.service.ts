import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Store, StoreSimple } from 'src/app/models/store';
import { departmentStore } from 'src/app/models/departmentStore';
import {api_url} from '../../../Global_var'

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor(private http: HttpClient) { }

  
  

  createStore(newStore:Store){
    return this.http.post(`${api_url}/store/storeRegister`,newStore);
  }

  updateStore(updatedData:StoreSimple){
    return this.http.post(`${api_url}/store/update`,updatedData);
  }

  addDepartment(depart : departmentStore){
    let storeId = parseInt(localStorage.getItem('idTienda') || "0")
    depart.idTienda = storeId
    //depart.idTienda = 1
    return this.http.post(`${api_url}/store/addDepartment`,depart);
  }
  removeDepartment(depart : departmentStore){
    let storeId = parseInt(localStorage.getItem('idTienda') || "0")
    depart.id= storeId
    //depart.id = 1
    return this.http.post(`${api_url}/store/deleteDepartment`,depart);
  }

  sendImage(img : any){
    let formData = new FormData();
    formData.append('fotografia', img,img.name);
    return this.http.post(`${api_url}/image/subirImagen`,formData);
  }

  getDepartment(){
    return this.http.get(`${api_url}/department/listar`);
  }

  getAddDepartment(){
    
    let storeId = {id : parseInt(localStorage.getItem('idTienda') || "0")}
    return this.http.post(`${api_url}/store/getDepartments`,storeId);
  }
}
