import { TestBed } from '@angular/core/testing';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
import { StoreSimple } from 'src/app/models/store';
import { UserSessionService } from './user-session.service';

describe('UserSessionService', () => {
  let service: UserSessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],
    });
    service = TestBed.inject(UserSessionService);
  });

  
  it('Unitaria: Comprobar Estado Del Login', () => {
    service.check()
    
    expect(service.loggedName == "noUser").toBeTruthy();
  });
  it('Unitaria: Cerrar Sesion', () => {
    service.logout();
    expect(service.isLoggedService ).toBeFalse();
  });
  it('Unitaria: Guardar Tienda', () => {
    let tiendaPrueba:StoreSimple = {
      nombre : "saved"
    } 
    service.saveStore(tiendaPrueba);
    expect(localStorage.getItem("nombreTienda") =="saved" ).toBeTruthy();
  });
});
