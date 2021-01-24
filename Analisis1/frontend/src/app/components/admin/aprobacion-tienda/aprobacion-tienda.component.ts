import { Component, OnInit } from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import { Router } from '@angular/router'

import { StoreService } from 'src/app/services/storeService/store.service';

import { FormularioAprobacion, StoreApprove } from '../../../models/formulario-aprobacion';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-aprobacion-tienda',
  templateUrl: './aprobacion-tienda.component.html',
  styleUrls: ['./aprobacion-tienda.component.css']
})
export class AprobacionTiendaComponent implements OnInit {
  
  storeInfo: StoreApprove = {
    id: 0,
    nombre: "",
    slogan: "",
    estado: -1,
    idU: -1,
    nombreU: "",
    apellido: "",
    correo: "",
    telefono: ""
  }

  idStore: FormularioAprobacion = {
    idTienda: -1
  }

  stores:any = [];

  constructor(private storeService: DataService, private toastr: ToastrService, private router: Router) { }

  ngOnInit(): void {
    this.getStores();
  }

  getStores(){
    this.storeService.getStores()
    .subscribe(
      (res) => {
        let arrayStore:any = res;
        this.processInformation(arrayStore);

      },
      err => {
        this.toastr.error("Error", err.error.msg);
      }
      )

  }

  processInformation(arrayStores: any){
    this.stores = arrayStores;
  }

  acceptStore(dataStore: StoreApprove){
    this.idStore.idTienda = dataStore.id;
    console.log(dataStore.id + "--" + dataStore.nombre + "--Acepto");
    this.storeService.postAuthorizeStore(this.idStore)
    .subscribe(
      (res) => {
        console.log(res);
        this.toastr.success("Aceptación",  "La tienda " + dataStore.nombre +"ha sido autorizada");
        this.router.navigate(['/admin/aprobaciontienda']);
        this.getStores();
      },
      err => {
        this.toastr.error("Error", err.error.msg);
      }
      )
  }

  rejectStore(dataStore: StoreApprove){
    this.idStore.idTienda = dataStore.id;
    console.log(dataStore.id + "--" + dataStore.nombre + "--Rechazo");
    this.storeService.postRejectStore(this.idStore)
    .subscribe(
      (res) => {
        console.log(res);
        this.toastr.success("Negación", "La tienda " + dataStore.nombre +" ha sido rechazada");
        this.router.navigate(['/admin/aprobaciontienda']);
        this.getStores();
      },
      err => {
        this.toastr.error("Error", err.error.msg);
      }
      )
  }

}
