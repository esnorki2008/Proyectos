import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router'
import { UserSessionService } from 'src/app/services/userSession/user-session.service';
@Component({
  selector: 'app-aprofile',
  templateUrl: './aprofile.component.html',
  styleUrls: ['./aprofile.component.css']
})
export class AprofileComponent implements OnInit {

  constructor(private sessionService : UserSessionService,private router: Router) { }

  ngOnInit(): void {
    this.sessionService.userHref(1);
  }

  verTiendas(){
    this.router.navigate(['/admin/aprobaciontienda'])
  }
}
