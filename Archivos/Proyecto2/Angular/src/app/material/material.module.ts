import { NgModule } from '@angular/core'
import{MatButtonModule} from '@angular/material/button';
import{MatToolbarModule}from '@angular/material/toolbar';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list'; 
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';

const MaterialComponents=[
  MatButtonModule,MatToolbarModule,MatDividerModule,MatListModule,
  MatDividerModule,MatFormFieldModule,MatDialogModule,MatInputModule,
  MatSelectModule
]

@NgModule({
  declarations: [],
  imports: [MaterialComponents],
  exports:[MaterialComponents]
})
export class MaterialModule { }
