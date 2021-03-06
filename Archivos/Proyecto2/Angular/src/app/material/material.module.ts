import { NgModule } from '@angular/core'
import{MatButtonModule} from '@angular/material/button';
import{MatToolbarModule}from '@angular/material/toolbar';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list'; 
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatTreeModule} from '@angular/material/tree'; 
import {MatIconModule} from '@angular/material/icon';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatDatepickerModule} from '@angular/material/datepicker';

const MaterialComponents=[
  MatButtonModule,MatToolbarModule,MatDividerModule,MatListModule,
  MatDividerModule,MatFormFieldModule,MatDialogModule,MatInputModule,
  MatSelectModule,MatTreeModule,MatIconModule,MatPaginatorModule,MatTableModule,
  MatCardModule,MatDatepickerModule
]

@NgModule({
  declarations: [],
  imports: [MaterialComponents],
  exports:[MaterialComponents]
})
export class MaterialModule { }
