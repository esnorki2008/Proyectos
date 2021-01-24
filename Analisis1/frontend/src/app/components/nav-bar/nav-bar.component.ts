import { Component, OnInit } from '@angular/core';
import { UserSessionService } from 'src/app/services/userSession/user-session.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private userSession:UserSessionService) { }
  //Variable booleana que controla los datos mostrados en el NavBar
  public isLogged: boolean = false;
  //Muestra el nombre del usuario actualmente conectado
  public nameUser: string = "Usuario";
  ngOnInit(): void {
    this.userSession.change.subscribe((isLoggedService :boolean) =>
          {this.isLogged = isLoggedService});
    this.userSession.changeName.subscribe((usrName : string) =>{
      this.nameUser = usrName
    })
    this.userSession.check(); 
  }

  logout(): void {
    this.userSession.logout();
  }
}
