import { TestBed } from '@angular/core/testing';

import { OrderReceptionService } from './order-reception.service';

describe('OrderReceptionService', () => {
  let service: OrderReceptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderReceptionService);
  });

});
