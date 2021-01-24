import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AprofileComponent } from './aprofile.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';

describe('AprofileComponent', () => {
  let component: AprofileComponent;
  let fixture: ComponentFixture<AprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],
      declarations: [ AprofileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
