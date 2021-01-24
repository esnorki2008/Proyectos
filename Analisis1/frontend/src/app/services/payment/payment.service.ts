import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {api_url} from '../../../Global_var'

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  buy(newItem:any){
    return this.http.post(`${api_url}/purchase/compra`,newItem);
  }
}
