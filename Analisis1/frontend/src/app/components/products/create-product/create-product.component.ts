import { Component, OnInit } from '@angular/core';
import { NgForm } from "@angular/forms";
import {ToastrService} from 'ngx-toastr';

import { Picture, Product, Product_Simple } from "../../../models/product";
import { CrudProductService } from "../../../services/product/crud-product.service";
@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  public name: string = "";
  public price: any = null;
  public amount: any = null;
  public category: string = "";
  public description: string = "";
  public picture: any = "";
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

  constructor(private toastr: ToastrService, private crud_product: CrudProductService) { }

  ngOnInit(): void {
    this.get_categories();
  }

  inputPicture() {
    document.getElementById('input_picture')?.click();
  }

  selectImage() {
    this.toastr.info("La imagen se ingreso de manera correcta", "Imagen agregada correctamente");
  }

  createProduct(){
    
    if (this.name != "" && this.price != null && this.picture != "" && this.amount != null && this.category != "" && this.description != ""){
      this.product_simple.nombre = this.name;
      this.product_simple.precio = this.price;
      this.product_simple.imagen = this.picture.ruta;
      this.product_simple.cantidad = this.amount;
      this.product_simple.category = this.category;
      this.product_simple.descripcion = this.description;
      this.product_simple.idTienda = parseInt(localStorage.getItem('idTienda') || "0");
      
      this.product_simple.idCategoria = this.category_id;
      console.log(this.product_simple)
      this.crud_product.postCreate(this.product_simple)
      .subscribe(
        (res) =>{
          this.toastr.success("Ingreso correcto del producto", "Ingreso exitoso");
          console.log(res);
          this.clean_data();
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
          this.picture = res;
        },
        err => {
          this.toastr.error("Error", err.error.msg);
          console.log(err);
        }
      )
    }
    
    return "Imagen";
  }

  clean_data(){
    this.name = "";
    this.price = null;
    this.amount = null;
    this.category = "";
    this.description = "";
    this.picture = "";
  }

  get_categories(){
    this.crud_product.getCategories()
    .subscribe(
      (res) => {
        let categories: any = res; 
        this.categories_names = res;
        this.category_id = categories[0].id;
        this.category = categories[0].nombre;
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
      console.log(this.category_id);
  }
}
