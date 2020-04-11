import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { InicioComponent } from './inicio/inicio.component';
import {LoginComponent} from './login/login.component'
import {AdminComponent}from './admin/admin.component'
const routes: Routes = [
  {path:'',redirectTo: '/home',pathMatch: 'full'},
  {path:'home',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent},
  {path: '**',redirectTo: '/home',pathMatch: 'full'}

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
