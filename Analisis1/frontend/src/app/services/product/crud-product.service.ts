import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Id_Product, Picture, Product } from "../../models/product";
import { Product_Simple } from "../../models/product";
import * as globales from "../../../Global_var";

@Injectable({
  providedIn: 'root'
})
export class CrudProductService {

  constructor(private http: HttpClient) { }

  postCreate(product: Product_Simple){
    return this.http.post(`${globales.api_url}/product/create`, product);
  }

  postInsertImage(image: any){
    return this.http.post(`${globales.api_url}/image/subirImagen`, image);
  }

  postModify(product: Product){
    return this.http.post(`${globales.api_url}/product/update`, product);
  }

  postDelete(idProduct: Id_Product){
    return this.http.post(`${globales.api_url}/delete_product`, idProduct);
  }

  getCategories() {
    return this.http.get(`${globales.api_url}/category/listar`);
  }
}
