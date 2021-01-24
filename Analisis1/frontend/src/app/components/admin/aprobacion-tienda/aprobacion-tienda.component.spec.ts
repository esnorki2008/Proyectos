import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AprobacionTiendaComponent } from './aprobacion-tienda.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';


describe('AprobacionTiendaComponent', () => {
  let component: AprobacionTiendaComponent;
  let fixture: ComponentFixture<AprobacionTiendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],
      declarations: [ AprobacionTiendaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AprobacionTiendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
