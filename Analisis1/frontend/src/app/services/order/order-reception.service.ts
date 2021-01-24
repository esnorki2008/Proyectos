import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import * as globales from "../../../Global_var";
import { Order, id_Order } from "../../models/order";

@Injectable({
  providedIn: 'root'
})
export class OrderReceptionService {

  constructor(private http: HttpClient) { }

  getOrders(){
    return this.http.get(`${globales.api_url}/order/listarEspera`);
  }

  acceptOrder(id_order: id_Order) {
    return this.http.post(`${globales.api_url}/order/aceptar`, id_order);
  }

  rejectOrder(id_order: id_Order) {
    return this.http.post(`${globales.api_url}/order/rechazar`, id_order);
  }
}
