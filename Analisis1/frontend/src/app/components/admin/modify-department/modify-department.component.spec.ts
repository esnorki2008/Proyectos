import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyDepartmentComponent } from './modify-department.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';


describe('ModifyDepartmentComponent', () => {
  let component: ModifyDepartmentComponent;
  let fixture: ComponentFixture<ModifyDepartmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],
      declarations: [ ModifyDepartmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyDepartmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
