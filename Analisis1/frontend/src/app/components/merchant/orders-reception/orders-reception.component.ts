import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { ToastrService } from 'ngx-toastr';

import { Order,id_Order } from "../../../models/order";
import { OrderReceptionService } from "../../../services/order/order-reception.service";

@Component({
  selector: 'app-orders-reception',
  templateUrl: './orders-reception.component.html',
  styleUrls: ['./orders-reception.component.css']
})
export class OrdersReceptionComponent implements OnInit {

  //Actualizar los modelos para que funcione al momento de trabajar con los
  public order: Order = {
    id: -1,
    idCompra: -1,
    nombreUsuario: "",
    direccion: "",
    estado: -1
  }

  public idOrder: id_Order = {
    idCompra: -1
  }
  
  public orders: any = [];

  constructor(private toastr: ToastrService, private order_service: OrderReceptionService, private router:Router) { }

  ngOnInit(): void {
    this.getOrders();
  }

  getOrders() {
    this.order_service.getOrders()
    .subscribe(
      (res) => {
        console.log(res);
        this.orders = res;
      },
      err => {
        this.toastr.error("Error", err.error.msg);
        console.log(err);
      }
      )
  }

  acceptOrder(order:Order){
    //Mejorar el manejo de lo que se necesita para autorizar el pedido
    this.idOrder.idCompra = order.id;
    //---------------------------------------------------------------
    this.order_service.acceptOrder(this.idOrder)
    .subscribe(
      (res) => {
        console.log(res);
        this.toastr.success("Aceptación",  "El pedido " + order.id +" ha sido autorizado");
        this.router.navigate(['/admin/orders']);
        this.getOrders();
      },
      err => {
        this.toastr.error("Error", err.error.msg);
      }
    );
  }

  rejectOrder(order:Order){
    //Mejorar el manejo de lo que se necesita para rechazar el pedido
    this.idOrder.idCompra = order.id;
    //---------------------------------------------------------------
    this.order_service.rejectOrder(this.idOrder)
    .subscribe(
      (res) => {
        console.log(res);
        this.toastr.success("Negación", "El pedido " + order.id +" ha sido rechazado");
        this.router.navigate(['/admin/orders']);
        this.getOrders();
      },
      err => {
        this.toastr.error("Error", err.error.msg);
      }
    );
  }
  
}
