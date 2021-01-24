import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CatalogoServiceService} from '../../../services/CatalogoService/catalogo-service.service'
import {Product} from '../../../models/product'
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.css']
})
export class ViewProductsComponent implements OnInit {
  filtername:string='';

  constructor(private CatalogService:CatalogoServiceService,private router:Router,private toastr:ToastrService) { }

  ngOnInit(): void {
   
    this.TipoBusqueda='StoreProduct'
    this.ObtenerIdMiTienda();

  }
  // contexto
  ArrayContext:any=[]
  // filtro
  TipoBusqueda:any=[]
  // id vendedor
  IdStore:number=0;

  productos:any = [];


 Editar(Producto:any){
  // redireccionar a la pagina de jose
  localStorage.setItem("producto",JSON.stringify(Producto));
  this.router.navigate(['products/modifyanddelete'])

}


ProductosGeneral(id:any){
  let BodyHttp: any = {
    "id": id
  };
  this.CatalogService.GetProductosTienda(BodyHttp).subscribe(
    async res => {
      this.productos = res;
      this.ArrayContext = this.productos;
    },
    async err => {
      this.MensajeError('No Se Han Podido Cargar Los Productos');
      this.productos = [];
    }

  )
}



MensajeError(mensaje:any){
  this.toastr.error('Error',mensaje);
}

MensajeExito(){
  this.toastr.success('Registro','Se Ha Registrado Con Exito');
}


// Metodo de captura de buscador

onKey(event:any){

  if  (this.TipoBusqueda == "StoreProduct" && this.filtername !=''){ // Buscar depa con filtro
    let  v = this.ArrayContext.filter((product: { nombre: string; }) => (product.nombre).toLowerCase().includes(this.filtername.toLowerCase()));
    this.productos = v;
  }else if (this.TipoBusqueda == "StoreProduct" && this.filtername == ''){ // Volver a ver todos los  depas
    this.ProductosGeneral(this.IdStore);
  }
}

VerTodo(){
  // mandar a todo de nuevo
  this.ProductosGeneral(Number(this.IdStore));
 
}

 ObtenerIdMiTienda(){
    let idstore = localStorage.getItem('idTienda');
    if (idstore == null) {
      this.router.navigate(['login']);
    } else {
      // mando a llamar los productos de esa tienda
      try {
        this.IdStore=Number(idstore);
        this.ProductosGeneral(Number(idstore));

      } catch (error) {
        this.router.navigate(['login']);
      }
    }
  }

}
