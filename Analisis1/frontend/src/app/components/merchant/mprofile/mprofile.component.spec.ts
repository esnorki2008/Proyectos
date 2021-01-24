import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MprofileComponent } from './mprofile.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';

describe('MprofileComponent', () => {
  let component: MprofileComponent;
  let fixture: ComponentFixture<MprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],

      declarations: [ MprofileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
