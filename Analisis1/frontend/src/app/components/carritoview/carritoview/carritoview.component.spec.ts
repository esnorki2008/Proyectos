import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarritoviewComponent } from './carritoview.component';

describe('CarritoviewComponent', () => {
  let component: CarritoviewComponent;
  let fixture: ComponentFixture<CarritoviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarritoviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarritoviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
