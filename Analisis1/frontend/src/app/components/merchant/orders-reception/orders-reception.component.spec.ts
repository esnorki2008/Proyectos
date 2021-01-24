import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersReceptionComponent } from './orders-reception.component';

describe('OrdersReceptionComponent', () => {
  let component: OrdersReceptionComponent;
  let fixture: ComponentFixture<OrdersReceptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrdersReceptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdersReceptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
