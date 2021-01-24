import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { itemCarrito, ventaCarrito } from 'src/app/models/product';
import { UserSessionService } from 'src/app/services/userSession/user-session.service';
import { api_url } from 'src/Global_var';
import { ToastrService } from 'ngx-toastr'; 
import { PaymentService } from 'src/app/services/payment/payment.service';
import { element } from 'protractor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})

export class PaymentComponent implements OnInit {


  imgVisa: string = ""
  imgMaster: string = ""
  imgMurica: string = ""
  imgPaypal: string = ""
  fecha: string = "HOY"
  products: any = [];
  total: number = 0;
  tax: number = 0
  subtotal: number = 0
  seguridad : string = "";
  tarjeta : string = "";
  nombre : string ="";
  fechaValida : string = "";
  transact : boolean = false;
  direccion : string = "";
  constructor(private sesion: UserSessionService,private toastr: ToastrService,private pago:PaymentService,
    private router: Router) {
  


  }

  ngOnInit(): void {
    this.transact = false;
    this.total = 0;
    this.subtotal = 0;
    this.tax = 0;
    this.sesion.userHref(2);
    let productoscarrito: any = localStorage.getItem('carrito');
    if (productoscarrito == null || productoscarrito == '') {
      this.products = [];
      this.router.navigate(['']);
    } else {
      let carrito = JSON.parse(productoscarrito);
      this.products = carrito;
      this.products.forEach((element: itemCarrito) => {
        this.subtotal = this.subtotal + (element.precio || 0) * (element.CantidadCompra || 0)
        element.cantidad = element.CantidadCompra
      });
      this.tax = this.subtotal * 0.12;
      this.tax =  Math.round((this.tax)) ;
      this.total = Math.round((this.tax + this.subtotal));
    }

    this.imgVisa = api_url + "/credit/visa.png"
    this.imgMaster = api_url + "/credit/mastercard.png"
    this.imgMurica = api_url + "/credit/american-express.png"
    this.imgPaypal = api_url + "/credit/paypal2.png"

    let currentDate = new Date()
    this.fecha = formatDate(currentDate, 'dd/MM/YYYY', 'en-Us')

  }
  confirm(): void {
    let regexp = new RegExp('([0-9]{4}-){3}([0-9]{4})'),
    test = regexp.test(this.tarjeta);
    if(!test){
      this.toastr.error("No se Ingreso Una Tarjeta Valida",'Error')
      return;
    }
    regexp = new RegExp('([0-9]{3})'),
    test = regexp.test(this.seguridad);
    if(!test){
      this.toastr.error("No se Ingreso Un Codigo De Seguridad Valido",'Error')
      return;
    }
    if(this.nombre = ""){
      this.toastr.error("El nombre esta vacio",'Error')
      return;
    }
    regexp = new RegExp('([0-9]{2}/[0-9]{2})'),
    test = regexp.test(this.fechaValida);
    if(!test){
      this.toastr.error("No se Ingreso Una Fecha Valida",'Error')
      return;
    } 
    if(this.products == []){
      this.toastr.error("No hay productos que comprar",'Error')
      return;
    }
    if(this.total == 0){
      this.toastr.error("No se puede comprar si el total es cero",'Error')
      return;
    }
    
    if(this.direccion == ""){
      this.toastr.error("La direccion de entrega esta vacia",'Error')
      return;
    }

    let ident :number= parseInt(localStorage.getItem("userId") || "0");
    if(ident==0){
      this.toastr.error("No se puede comprar si no hay usuario",'Error')
      return;
    }



    this.toastr.info("Comenzando Transaccion",'Aviso')
    let send:ventaCarrito = {
      total : this.total,
      idUsuario : ident,
      productos: this.products,
      direccion: this.direccion
    }
    this.transact = true;
    this.pago.buy(send).subscribe(res =>{
      this.toastr.success("La Compra Fue Realizada Con Exito",'Aviso')
      this.VaciarCarrito()
      this.transact = false;
      this.router.navigate(['']);
    },err=>{
      this.toastr.error("La transaccion no se pudo completar, revise el stock del producto",'Error')
      this.transact = false;
    });
  }
  VaciarCarrito(){
    localStorage.removeItem('carrito')
    this.products=[];
  }
}
