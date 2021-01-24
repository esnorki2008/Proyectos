import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CprofileComponent } from './cprofile.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
describe('CprofileComponent', () => {
  let component: CprofileComponent;
  let fixture: ComponentFixture<CprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],

      declarations: [ CprofileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
