import { TestBed } from '@angular/core/testing';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
import { DepartmentService } from './department.service';

describe('DepartmentService', () => {
  let service: DepartmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],
    });
    service = TestBed.inject(DepartmentService);
  });

});
