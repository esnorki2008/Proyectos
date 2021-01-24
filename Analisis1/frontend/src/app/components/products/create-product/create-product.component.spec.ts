import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProductComponent } from './create-product.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
describe('CreateProductComponent', () => {
  let component: CreateProductComponent;
  let fixture: ComponentFixture<CreateProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],

      declarations: [ CreateProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('Prueba unitaria: Limpieza de datos', () => {
    component.clean_data();
    expect(component.name).toEqual("");
    expect(component.price).toBeNull();
    expect(component.category).toEqual("");
    expect(component.amount).toBeNull();
    expect(component.description).toEqual("");
    expect(component.picture).toEqual("");

  });
});
