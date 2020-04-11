import { Component, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { HomeComponent } from "../home/home.component";

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  estado:number = 1;
  constructor() { }


  
  ngOnInit(): void {
    
  }

  change():void{
  }


  Mandar(confirmation: number){
    this.estado=confirmation;
    console.log(this.estado);
  }
}
