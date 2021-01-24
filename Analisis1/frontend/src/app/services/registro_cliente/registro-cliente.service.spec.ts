import { TestBed } from '@angular/core/testing';

import { RegistroClienteService } from './registro-cliente.service';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
describe('RegistroClienteService', () => {
  let service: RegistroClienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],
    });
    service = TestBed.inject(RegistroClienteService);
  });

});
