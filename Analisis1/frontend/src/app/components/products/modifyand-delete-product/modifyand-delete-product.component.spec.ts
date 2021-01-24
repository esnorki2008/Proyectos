import { ComponentFixture, TestBed } from '@angular/core/testing';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';

import { ModifyandDeleteProductComponent } from './modifyand-delete-product.component';

describe('ModifyandDeleteProductComponent', () => {
  let component: ModifyandDeleteProductComponent;
  let fixture: ComponentFixture<ModifyandDeleteProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],
      declarations: [ ModifyandDeleteProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyandDeleteProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
