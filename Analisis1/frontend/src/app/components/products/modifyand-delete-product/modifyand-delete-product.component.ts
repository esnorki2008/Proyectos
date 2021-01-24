import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import {ToastrService} from 'ngx-toastr';

import { Id_Product, Product, Product_Simple } from "../../../models/product";
import { CrudProductService } from "../../../services/product/crud-product.service";

@Component({
  selector: 'app-modifyand-delete-product',
  templateUrl: './modifyand-delete-product.component.html',
  styleUrls: ['./modifyand-delete-product.component.css']
})
export class ModifyandDeleteProductComponent implements OnInit {

  public producto = JSON.parse(localStorage.getItem("producto") || "{}");

  public id: number = this.producto.id;
  public name: string = this.producto.nombre;
  public price: any = this.producto.precio;
  public amount: any = this.producto.cantidad;
  public category: string = "";
  public description: string = this.producto.descripcion;
  public picture: any = this.producto.imagen;
  public categories_names: any = "";
  public select_option: string = "";
  public category_id: number = -1;

  

  product_complete: Product = {
    id: -1,
    nombre: "",
    precio: 0.0,
    imagen: '',
    cantidad: 0,
    category: "",
    descripcion: ""
  }

  product_simple: Product_Simple ={
    nombre: "",
    precio: 0.0,
    imagen: '',
    cantidad: 0,
    category: "",
    descripcion: ""
  }

  idProduct: Id_Product = {
    id: -1
  }
  constructor(private toastr:ToastrService, private crud_product:CrudProductService, private router: Router) { }

  ngOnInit(): void {
    this.get_categories();
    this.select_category();
    console.log(this.producto);
  }

  insertImage(event: Event){
    const element = event.currentTarget as HTMLInputElement;
    let files : any = element.files;
    let image = files;
    if(image.length == 0){
      return "";
    }
    else {
      const picture_ = image[0];
      console.log(picture_);
      const picture_form = new FormData();
      picture_form.append('fotografia', picture_);
      this.crud_product.postInsertImage(picture_form)
      .subscribe(
        (res) => {
          console.log(res);
          let image_: any = res;
          this.picture = image_.ruta;
        },
        err => {
          this.toastr.error("Error", err.error.msg);
          console.log(err);
        }
      )
    }
    
    return "Imagen";
  }

  inputPicture() {
    document.getElementById('input_picture')?.click();
  }

  selectImage() {
    this.toastr.info("La imagen se ingreso de manera correcta", "Imagen agregada correctamente");
  }

  saveChanges() {
    if (this.name != "" && this.price != null && this.picture != "" && this.amount != null && this.category != "" && this.description != ""){
      this.product_complete.id = this.id;
      this.product_complete.nombre = this.name;
      this.product_complete.precio = this.price;
      this.product_complete.imagen = this.picture;
      this.product_complete.cantidad = this.amount;
      this.product_complete.category = this.category;
      this.product_complete.descripcion = this.description;
      this.product_complete.idCategoria = this.category_id;
      this.product_complete.idTienda = parseInt(localStorage.getItem('idTienda') || "0");
      
      this.crud_product.postModify(this.product_complete)
      .subscribe(
        (res) => {
          this.toastr.success("Modificación correcta del producto", "Modificación exitosa");
          console.log(res);
          this.router.navigate(["/AdminStore/MyProducts"]);
        },
        err => {
          this.toastr.error("Error", err.error.msg);
        }
      )
    }
    else{
      if(this.name == "") {
        this.toastr.warning("El espacio de nombre se encuentra vacío", "Valor faltante");
      }
      if(this.picture == "") {
        this.toastr.warning("No se selecciono ninguna imagen", "Valor faltante");
      }
      if (this.price == null) {
        this.toastr.warning("El espacio de precio se encuentra vacío", "Valor faltante");
      }
      if (this.amount == null) {
        this.toastr.warning("El espacio de cantidad se encuentra vacío", "Valor faltante");
      }
      if (this.category == "") {
        this.toastr.warning("El espacio de categoria se encuentra vacío", "Valor faltante");
      }
      if (this.description == "") {
        this.toastr.warning("El espacio de descripcion se encuentra vacío", "Valor faltante");
      }
    }
  }

  deleteProduct() {
    this.crud_product.postDelete(this.idProduct)
    .subscribe(
      (res) => {
        this.toastr.success("Eliminación correcta del producto", "Eliminación completada");
          console.log(res);
      },
      err => {
        this.toastr.error("Error", err.error.msg);
      }
    )
  }
  
  get_categories(){
    this.crud_product.getCategories()
    .subscribe(
      (res) => {
        let categories: any = res; 
        this.categories_names = res;
        console.log(this.categories_names);
        this.category_id = categories[0].id;
        this.category = categories[0].nombre;
        for(let i:number = 0; i<this.categories_names.length; i++){
          if(this.producto.nombreCategoria == this.categories_names[i].nombre){
            this.category_id = this.categories_names[i].id;
            this.category = this.categories_names[i].nombre;
            this.select_option = this.categories_names[i].nombre;
          }
        }
      }, 
      err => {
        this.toastr.error("No hay categorias", "Error");
          console.log(err);
      }
    );
  }
  
  capture_data(){
      for(let i:number = 0; i<this.categories_names.length; i++){
        if(this.select_option == this.categories_names[i].nombre){
          this.category_id = this.categories_names[i].id;
          this.category = this.categories_names[i].nombre;
        }
      }
  }

  select_category(){
    console.log(this.categories_names);
    
  }
}
