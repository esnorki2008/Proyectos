import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AprofileComponent } from './components/admin/aprofile/aprofile.component';
import { CprofileComponent } from './components/client/cprofile/cprofile.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MprofileComponent } from './components/merchant/mprofile/mprofile.component';
import { Page404Component } from './components/page404/page404.component';
import { RegisterComponent } from './components/register/register.component';
import { ViewStoreComponent } from './components/view-store/view-store.component';
import { RegisterStoreComponent } from './components/register-store/register-store.component';
import { AprobacionTiendaComponent } from './components/admin/aprobacion-tienda/aprobacion-tienda.component';
import {ViewProductsComponent} from './components/StoreAdminViewProduct-Depa/view-products/view-products.component';

import { ViewDepartmentsComponent } from './components/merchant/view-departments/view-departments.component';
import { CreateProductComponent } from './components/products/create-product/create-product.component';
import { ModifyandDeleteProductComponent } from './components/products/modifyand-delete-product/modifyand-delete-product.component';
import { CreateDepartmentComponent } from './components/admin/create-department/create-department.component';
import { ListDepartmentsComponent } from './components/admin/list-departments/list-departments.component';
import { ModifyDepartmentComponent } from './components/admin/modify-department/modify-department.component';
import { StorePanelComponent } from './components/merchant/store-panel/store-panel.component';
import { OrdersReceptionComponent } from './components/merchant/orders-reception/orders-reception.component';
import {CarritoviewComponent} from './components/carritoview/carritoview/carritoview.component';
import { PaymentComponent } from './components/client/payment/payment.component';
import { ReportComponent } from './components/report/report.component';
const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'admin/profile', component: AprofileComponent},
  { path: 'client/profile', component: CprofileComponent},
  { path: 'client/payment', component: PaymentComponent},
  { path: 'merchant', component: MprofileComponent},
  { path: 'store', component: ViewStoreComponent}, // vista de los productos no tomar
  { path: 'register/store', component: RegisterStoreComponent},
  { path: 'admin/aprobaciontienda', component: AprobacionTiendaComponent},
  {path:'AdminStore/MyProducts',component:ViewProductsComponent},
  { path: 'merchant/department', component: ViewDepartmentsComponent},
  { path: 'merchant/panel', component: StorePanelComponent},
  { path: 'report', component: ReportComponent},
  { path: 'products/create', component:CreateProductComponent},
  { path: 'products/modifyanddelete', component:ModifyandDeleteProductComponent},
  { path: 'admin/department/create', component:CreateDepartmentComponent},
  { path: 'admin/department/list', component:ListDepartmentsComponent},
  { path: 'admin/department/modify', component:ModifyDepartmentComponent},
  { path: 'admin/orders', component:OrdersReceptionComponent},
  {path: 'viewcarrito',component:CarritoviewComponent},
  { path: '**', component: Page404Component}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
