import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CatalogoServiceService} from '../../services/CatalogoService/catalogo-service.service'
import * as globales from '../../../Global_var';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-view-store',
  templateUrl: './view-store.component.html',
  styleUrls: ['./view-store.component.css']
})
export class ViewStoreComponent implements OnInit {

  // variable de captura de filtro
  filtername:string='';
  // variable que define busqueda en tienda o depa
  TipoBusqueda:string='';
  // contexto a buscar
  ArrayContext:any=[];
  // contexto de tienda
  TiendaContext:any=[];
  // contexto departamento
  DepartamentoContext:any=[];


  constructor(private CatalogService:CatalogoServiceService,private router:Router,private toastr:ToastrService) { }
  // Productos
   productos:any = [];
  // tiendas
  tiendas:any = [];
  // departamentos
  departamentos:any=[];

  ngOnInit(): void {
    this.TiendaClick();

  }


  VerIndividual(Producto:any){

  }


  ProductosDeTiendaID(id:any){
    let BodyHttp:any = {
      "id":id
    };
  
    this.CatalogService.GetProductosTienda(BodyHttp).subscribe(
      async res =>{

        let addimagepath:any=[];
        addimagepath =res;
        addimagepath.forEach((produ: { imagen: string; }) =>{
          produ.imagen = globales.api_url+'/'+produ.imagen;
        })
        
        this.productos=addimagepath;
        this.ArrayContext=this.productos;
      },
      async err =>{
          this.MensajeError('No Se Han Podido Cargar Los Productos');
          this.productos=[];
      }
      )
  }


  TiendasDeDepartamento(id:any){
    let BodyHttp:any = {
      "id":id
    };
    this.CatalogService.ObtenerTiendasDepartamento(BodyHttp).subscribe(
      async res =>{
        
        this.tiendas=res;
        this.ArrayContext=this.tiendas;
      },
      async err =>{
          this.MensajeError('No Se Han Podido Cargar Las Tiendas');
          this.tiendas=[];
      }
      )
  }

  ProductosFiltrados(filtro:any){
    // por el momento no necesario
  }

  MensajeError(mensaje:any){
    this.toastr.error('Error',mensaje);
  }

  MensajeExito(){
    this.toastr.success('Registro','Se Ha Registrado Con Exito');
  }


  // Metodo de captura de buscador

  onKey(event:any){
    this.MetodoSeleccionDeBusqueda();
  }

  MetodoSeleccionDeBusqueda(){
    if  (this.TipoBusqueda == "AllDepa" && this.filtername !=''){ // Buscar depa con filtro
      let  v = this.ArrayContext.filter((depa: { departamento: string; }) => (depa.departamento).toLowerCase().includes(this.filtername.toLowerCase()));
      this.departamentos = v;
    }else if (this.TipoBusqueda == "AllDepa" && this.filtername == ''){ // Volver a ver todos los  depas
      this.DepartamentosClick();
  

    } else if(this.TipoBusqueda =="AllStore" && this.filtername!=''){ // Buscar store con filtro
      let  v = this.ArrayContext.filter((tienda: { nombreTienda: string; }) => (tienda.nombreTienda).toLowerCase().includes(this.filtername.toLowerCase()));
      this.tiendas = v;
    }else if(this.TipoBusqueda =="AllStore" && this.filtername ==''){ // Volver a ver todas las store
      this.TiendaClick();
    
    }else if(this.TipoBusqueda=='StoreProduc' && this.filtername!=''){
      let  v = this.ArrayContext.filter((producto: { nombre: string; }) => (producto.nombre).toLowerCase().includes(this.filtername.toLowerCase()));
      this.productos = v;
    }else if(this.TipoBusqueda =='StoreProduc' && this.filtername == ''){
      this.ProductosDeTiendaID(this.TiendaContext);
    
    }else if(this.TipoBusqueda =='TiendaFiltradoDepa' && this.filtername != ''){
      let  v = this.ArrayContext.filter((tienda: { nombreTienda: string; }) => (tienda.nombreTienda).toLowerCase().includes(this.filtername.toLowerCase()));
      this.tiendas = v;
      
    }else if(this.TipoBusqueda == 'TiendaFiltradoDepa' && this.filtername ==''){
      this.TiendasDeDepartamento(this.DepartamentoContext);
    }
  }


  // -------------- CLICK TODOS  TIENDAS DEL SISTEMA --------
  TiendaClick(){
    // mandar a traer las tiendas
    this.TipoBusqueda='AllStore';
    this.productos=[];
    this.departamentos=[];
    // Obtencion de las todas las tiendas
    this.GetAllStores();

  }
  // ------------------- CLICK TODOS DEPARTAMENTOS DEL SISTEMA -----------------------
  DepartamentosClick(){
    this.TipoBusqueda="AllDepa";
    this.productos=[];
    this.tiendas=[];
    // Obtencion de todos los sistemas
    this.GetAllDepartments();
  }

  // --------------------- VER LOS PRODUCTOS EN ESPECIFICO DE UNA TIENDA ----------
  verProductosTienda(tienda:any){
    
    this.TiendaContext=tienda.id;
    this.TipoBusqueda = "StoreProduc";
    this.departamentos=[];
    this.tiendas=[];
    this.ProductosDeTiendaID(tienda.id);
    
  
  }
  // -------------------- VER LAS TIENDAS EN ESPECIFICO DE UN DEPA ------------------
  VerTiendasDepa(departamento:any){
    // mando el id del depa
    this.DepartamentoContext=departamento.id;
    this.TipoBusqueda='TiendaFiltradoDepa';
    this.productos=[];
    this.departamentos=[];
    // llamar a tiendas de depa
    this.TiendasDeDepartamento(departamento.id);
  }


  //_----------------- OBTENER TODAS LAS TIENDAS -----------------
  GetAllStores(){
    this.CatalogService.ObtenerTiendasGeneral().subscribe(
      res => {
        this.tiendas = res

        this.ArrayContext = this.tiendas;
      },
      err => {
        this.MensajeError('No Se Han Podido Cargar Las Tiendas');
        this.tiendas=[];
      }

    )
  }

  // ------------------ OBTENER TODOS LOS DEPARTAMENTOS ----------------
  GetAllDepartments(){
    this.CatalogService.ObtenerDepartamentosGeneral().subscribe(
      res => {
        this.departamentos = res
        this.ArrayContext = this.departamentos;
      },
      err => {
        this.MensajeError('No Se Han Podido Cargar Los Departamentos');
        this.departamentos=[];
      }

    )
  }

  Addcarrito(producto:any){
    let data = localStorage.getItem('userEmail');
    if(data !=''){
    this.MetodoAñadir(producto);
    }else{
      this.router.navigate(['login'])
    }
  }

  MetodoAñadir(aadproducto:any){
    try{
    var productoscarrito =localStorage.getItem('carrito');
    if (productoscarrito == null){
    
      let carrito = [];
      let nuevoP = this.cambiarCompra(aadproducto);
      carrito.push(nuevoP);
      localStorage.setItem('carrito',JSON.stringify(carrito));
      this.toastr.success('Carrito','Producto Añadido!');
    }else{
      
      let carrito = JSON.parse(productoscarrito);
      let nuevoP = this.cambiarCompra(aadproducto);
      carrito.push(nuevoP);
      localStorage.setItem('carrito',JSON.stringify(carrito));
      this.toastr.success('Carrito','Producto Añadido!');
    }

    }catch(err){
      this.MensajeError('No se ha podido agregar el producto');
    }
  }

  cambiarCompra(producto:any){
    let productoCompra = {
      'cantidadInventario':producto.cantidad,
      'descripcion':producto.descripcion,
      'id':producto.id,
      'imagen':producto.imagen,
      'nombre':producto.nombre,
      'nombreCategoria':producto.nombreCategoria,
      'nombreTienda':producto.nombreTienda,
      'precio':producto.precio,
      'CantidadCompra':1
    }
    return productoCompra;
  }

}
