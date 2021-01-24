import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import {  RouterModule } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';


import { CatalogoServiceService } from './catalogo-service.service';

describe('CatalogoServiceService', () => {
  let service: CatalogoServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],
    });
    service = TestBed.inject(CatalogoServiceService);
  });

});
