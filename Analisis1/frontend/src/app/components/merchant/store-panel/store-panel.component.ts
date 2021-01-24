import { Component, OnInit } from '@angular/core';
import { Store, StoreSimple } from 'src/app/models/store';
import { Router } from '@angular/router';
import { StoreService } from 'src/app/services/storeService/store.service';
import { api_url } from '../../../../Global_var'
import { ToastrService } from 'ngx-toastr';
import { UserSessionService } from 'src/app/services/userSession/user-session.service';
import { resetFakeAsyncZone } from '@angular/core/testing';
interface imgResponse {
  ruta?: string;
}

@Component({
  selector: 'app-store-panel',
  templateUrl: './store-panel.component.html',
  styleUrls: ['./store-panel.component.css']
})


export class StorePanelComponent implements OnInit {

  constructor(private tiendaService: StoreService, private router: Router, private toastr: ToastrService,
    private sessionService: UserSessionService) { }
  tienda: StoreSimple = {
    nombreTienda: "",
    slogan: "",
    idUsuario: 0,
    estado: 0,
    imagen: "",
    id: 0
  }
  imgData: any = "";
  blob: any = "";
  imgApi: imgResponse = {
    ruta: ""
  }
  ngOnInit(): void {
    this.sessionService.userHref(3);
    this.tienda.id = parseInt(localStorage.getItem('idTienda') || "0");
    this.tienda.nombreTienda = localStorage.getItem('nombreTienda') || "";
    this.tienda.slogan = localStorage.getItem('sloganTienda') || "";
    this.tienda.estado = parseInt(localStorage.getItem('estadoTienda') || "0");
    this.tienda.imagen = localStorage.getItem('imagenTienda') || "";
    this.tienda.idUsuario = parseInt(localStorage.getItem('userId') || "0");
    this.imgData = api_url + "/"+this.tienda.imagen;
    this.blob = null;
  }

  updateStore(): void {
    this.tiendaService.updateStore(this.tienda).subscribe(res => {
      this.toastr.success("Tienda  Actualizada ", 'Aviso');
      this.tienda.nombre = this.tienda.nombreTienda; 
      this.sessionService.saveStore(this.tienda)
      
    },
      err => {
        
        this.toastr.error("No Se Pudo Actualizar La Tienda", 'Error')
      })
  }

  hrefMechant(): void {
    this.router.navigate(['merchant']);
  }

  inputPicture(event: any) {
    if (event.target.files.length === 0)
      return;

    var mimeType = event.target.files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.toastr.warning("Solo Se Pueden Subir Imagenes", "Advertencia");
      return;
    }
    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (_event) => {
      this.imgData = reader.result;
    }
    this.blob = event.target.files[0]
  }
  sendImage() {
    if (this.blob !== null) {

      this.tiendaService.sendImage(this.blob).subscribe(
        res => {
          this.toastr.info("Imagen Subida ", 'Aviso');
          this.imgApi = res
          this.tienda.imagen = this.imgApi.ruta
          this.updateStore()
        },
        err => {
          console.log(err)
          this.toastr.error("No Se Pudo Actualizar La Imagen", 'Error')
        }
      );
    } else {
      this.updateStore()
    }


  }



}
