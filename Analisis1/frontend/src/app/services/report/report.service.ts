import { Injectable } from '@angular/core';

import {api_url} from '../../../Global_var'
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ReportService {
  constructor(private http: HttpClient) { }
  historialMensual(Anio:number,Mes: number) {
    return this.http.post(`${api_url}/order/reporteMes`,{year:Anio,month: Mes});
  }
  historialAnual(Anio:number) {
    return this.http.post(`${api_url}/order/reporteYear`,{year:Anio});
  }
}
