import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexVisitanteComponent } from './index-visitante.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
describe('IndexVisitanteComponent', () => {
  let component: IndexVisitanteComponent;
  let fixture: ComponentFixture<IndexVisitanteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],

      declarations: [ IndexVisitanteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndexVisitanteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
