import { ComponentFixture, TestBed } from '@angular/core/testing';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
import { RegisterStoreComponent } from './register-store.component';

describe('RegisterStoreComponent', () => {
  let component: RegisterStoreComponent;
  let fixture: ComponentFixture<RegisterStoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      
imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],

      declarations: [ RegisterStoreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
