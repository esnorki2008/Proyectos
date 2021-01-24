import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListDepartmentsComponent } from './list-departments.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';

describe('ListDepartmentsComponent', () => {
  let component: ListDepartmentsComponent;
  let fixture: ComponentFixture<ListDepartmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],

      declarations: [ ListDepartmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListDepartmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
