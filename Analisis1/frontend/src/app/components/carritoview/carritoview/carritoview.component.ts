import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import * as globales from '../../../../Global_var';
import {ToastrService} from 'ngx-toastr';
import { element } from 'protractor';

@Component({
  selector: 'app-carritoview',
  templateUrl: './carritoview.component.html',
  styleUrls: ['./carritoview.component.css']
})
export class CarritoviewComponent implements OnInit {

  constructor(private router:Router,private toastr:ToastrService) { }

  productos:any=[];


  ngOnInit(): void {
    let data = localStorage.getItem('userEmail');
    if(data !=''){
      // dejar ver
      }else{
        this.router.navigate(['login'])
      }

    var productoscarrito:any =localStorage.getItem('carrito');
    if (productoscarrito == null || productoscarrito == ''){
       this.productos =[];
    }else{
      let carrito = JSON.parse(productoscarrito);
      this.productos=carrito;
      
    }
  }

  VaciarCarrito(){
    localStorage.removeItem('carrito')
    this.productos=[];
  }

  EliminarProducto(producto:any){
    let contador =0;
    this.productos.forEach((element: { id: any; }) => {
      if (element.id == producto.id){
        this.productos.splice(contador,1);
        localStorage.setItem('carrito',JSON.stringify(this.productos));
      }
      contador++;
    });
  }

  async ConfirmarCompra(){
      let verificacion = 0;

      this.productos.forEach((element: any) =>{
        if (element.CantidadCompra<=0){
          this.toastr.error('Cantidad Erronea en '+element.nombre,'La cantidad a elegir debe ser mayor a 0');
          verificacion=1;
        }else if(element.CantidadCompra > element.cantidadInventario){
          this.toastr.error('Cantidad Erronea en '+element.nombre,'Cantidad Fuera de Inventario');
          verificacion=1;
        }
      });

      if(verificacion==0 && this.productos.length >0){
        localStorage.setItem('carrito',JSON.stringify(this.productos))
        console.log(this.productos)
        this.toastr.success('Compra','Su Compra Se Ha Realizado Con Exito');
        this.router.navigate(['/client/payment']);
      }else if(this.productos == [] || this.productos ==''){
        this.toastr.error('Debe Añadir Productos Primero');
      }
  }

}
