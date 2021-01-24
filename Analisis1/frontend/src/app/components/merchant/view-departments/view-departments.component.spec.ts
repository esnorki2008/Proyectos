import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDepartmentsComponent } from './view-departments.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
describe('ViewDepartmentsComponent', () => {
  let component: ViewDepartmentsComponent;
  let fixture: ComponentFixture<ViewDepartmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],

      declarations: [ ViewDepartmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDepartmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
