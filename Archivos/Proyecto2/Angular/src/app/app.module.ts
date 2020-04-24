import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import { MaterialModule } from './material/material.module';
import { InicioComponent } from './inicio/inicio.component';
import {LoginService} from './services/login.service';
import { UsuarioComponent } from './usuario/usuario.component';
import { TiendaComponent } from './tienda/tienda.component';
import { BusquedaComponent } from './tienda/busqueda/busqueda.component';
import { RegistrarComponent } from './registrar/registrar.component'
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    AdminComponent,
    InicioComponent,
    UsuarioComponent,
    TiendaComponent,
    BusquedaComponent,
    RegistrarComponent
  ],
  imports: [
    MaterialModule,
    BrowserModule,
    BrowserAnimationsModule,    
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule

  ],
  providers: [
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
