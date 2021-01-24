import { Component, OnInit } from '@angular/core';
import { UserSessionService } from 'src/app/services/userSession/user-session.service';
import { Router } from '@angular/router';
import { api_url } from '../../../../Global_var'
@Component({
  selector: 'app-mprofile',
  templateUrl: './mprofile.component.html',
  styleUrls: ['./mprofile.component.css']
})
export class MprofileComponent implements OnInit {
  tiendaNombre:string = ""
  tiendaSlogan:string = ""
  tiendaImagen:string = ""
  constructor(private sessionService : UserSessionService,private router: Router) { }

  ngOnInit(): void {
    this.sessionService.userHref(3);
    this.tiendaNombre = localStorage.getItem('nombreTienda') || "";
    this.tiendaSlogan = localStorage.getItem('sloganTienda') || "";
    this.tiendaImagen = api_url + "/"+ localStorage.getItem('imagenTienda') || "";
  }
  hrefPanel() :void{
    this.router.navigate(['merchant/panel']);
  }
  hrefDepartments() :void{
    this.router.navigate(['merchant/department']);
  }
  hrefProducts() :void{
    this.router.navigate(['AdminStore/MyProducts']);
  }
  hrefProductsCreate() :void{
    this.router.navigate(['products/create']);
  }
}
