import { Component, OnInit,Output,EventEmitter } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @Output() emitEvent:EventEmitter<number> = new EventEmitter<number>();


  constructor() { }

  ngOnInit(): void {
    
  }

 

  toggleVideo():void{

  }
  PonerLogin():void{
    this.emitEvent.emit(1)
  }
}
