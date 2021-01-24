import { TestBed } from '@angular/core/testing';

import { CrudProductService } from './crud-product.service';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
describe('CrudProductService', () => {
  let service: CrudProductService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],
    });
    service = TestBed.inject(CrudProductService);
  });

});
