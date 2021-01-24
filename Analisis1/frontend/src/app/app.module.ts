import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { Page404Component } from './components/page404/page404.component';
import { RegisterStoreComponent } from './components/register-store/register-store.component';
import { CprofileComponent } from './components/client/cprofile/cprofile.component';
import { AprofileComponent } from './components/admin/aprofile/aprofile.component';
import { MprofileComponent } from './components/merchant/mprofile/mprofile.component';
import { ViewStoreComponent } from './components/view-store/view-store.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { ToastrModule } from 'ngx-toastr';
import { AprobacionTiendaComponent } from './components/admin/aprobacion-tienda/aprobacion-tienda.component';
import { ViewProductsComponent } from './components/StoreAdminViewProduct-Depa/view-products/view-products.component';
import { ViewDepartmentsComponent } from './components/merchant/view-departments/view-departments.component';  
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { CreateProductComponent } from './components/products/create-product/create-product.component';
import { ModifyandDeleteProductComponent } from './components/products/modifyand-delete-product/modifyand-delete-product.component';
import { CreateDepartmentComponent } from './components/admin/create-department/create-department.component';
import { ListDepartmentsComponent } from './components/admin/list-departments/list-departments.component';
import { ModifyDepartmentComponent } from './components/admin/modify-department/modify-department.component';
import { StorePanelComponent } from './components/merchant/store-panel/store-panel.component';
import { OrdersReceptionComponent } from './components/merchant/orders-reception/orders-reception.component';
import { CarritoviewComponent } from './components/carritoview/carritoview/carritoview.component';
import { PaymentComponent } from './components/client/payment/payment.component';
import { ReportComponent } from './components/report/report.component';
@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    Page404Component,
    RegisterStoreComponent,
    CprofileComponent,
    AprofileComponent,
    MprofileComponent,
    ViewStoreComponent,
    SideBarComponent,
    AprobacionTiendaComponent,
    ViewProductsComponent,
    ViewDepartmentsComponent,
    CreateProductComponent,
    ModifyandDeleteProductComponent,
    CreateDepartmentComponent,
    ListDepartmentsComponent,
    ModifyDepartmentComponent,
    StorePanelComponent,
    OrdersReceptionComponent,
    PaymentComponent,
    CarritoviewComponent,
    ReportComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],exports : [
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
