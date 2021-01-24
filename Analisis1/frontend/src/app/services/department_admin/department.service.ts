import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import * as globales from "../../../Global_var";
import { DepartmentAdmin, DepartmentName } from "../../models/department-admin";
@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private http: HttpClient) { }

   //Endpoint para la creación del departamento
  postCreateDepartment(department: DepartmentName){
    return this.http.post(`${globales.api_url}/department/createDepartment`, department);
  }

  //Endpoint para la edición del nombre del departamento
  postEditDepartment(department: DepartmentAdmin) {
    return this.http.post(`${globales.api_url}/department/updateDepartment`, department);
  }

  //Endpoint que genera una lista de departamentos creados
  getDepartments() {
    return this.http.get(`${globales.api_url}/department/listar`);
  }
}
