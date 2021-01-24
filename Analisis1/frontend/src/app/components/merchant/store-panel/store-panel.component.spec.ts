import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StorePanelComponent } from './store-panel.component';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
describe('StorePanelComponent', () => {
  let component: StorePanelComponent;
  let fixture: ComponentFixture<StorePanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      
imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],

      declarations: [ StorePanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StorePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
