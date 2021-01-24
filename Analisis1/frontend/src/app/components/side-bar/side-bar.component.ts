import { Component, OnInit } from '@angular/core';
import { UserSessionService } from 'src/app/services/userSession/user-session.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  constructor(private sessionService : UserSessionService,private router: Router) { }
  userName:string = "DUMMY USER"
  isLogged:boolean = false;
  userRol:number = 0
  ngOnInit(): void {
    
    this.sessionService.change.subscribe((isLoggedService :boolean) =>
          {this.isLogged = isLoggedService});
    this.sessionService.changeName.subscribe((usrName : string) =>{
      this.userName = usrName
    })
    this.sessionService.changeRol.subscribe((rol:number)=>{
      this.userRol = rol
    })

    this.sessionService.check();
    console.log(this.userRol)
    
  }
  hrefCarrito():void{
    this.router.navigate(['viewcarrito']);
  }
  hrefAdmin():void{
    this.router.navigate(['admin/profile']);
  }
  hrefClient():void{
    this.router.navigate(['client/profile']);
  }
  hrefStoreAdmin():void{
    this.router.navigate(['merchant']);
  }
  hrefReporte():void{
    this.router.navigate(['report']);
  }
 
}
