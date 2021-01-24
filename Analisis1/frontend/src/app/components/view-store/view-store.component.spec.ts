import { ComponentFixture, TestBed } from '@angular/core/testing';
import {  RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrModule} from 'ngx-toastr';
import { ViewStoreComponent } from './view-store.component';

describe('ViewStoreComponent', () => {
  let component: ViewStoreComponent;
  let fixture: ComponentFixture<ViewStoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule,HttpClientModule,RouterTestingModule,ToastrModule.forRoot()],

      declarations: [ ViewStoreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  // crear un it para comprabar que todas las variables estan inicializadas en any
  it(' Unitaria Comprobar Arrays Vacios',()=>{
    expect(component.filtername).toEqual('');
    expect(component.TiendaContext).toEqual([]);
    expect(component.DepartamentoContext).toEqual([]);
    expect(component.productos).toEqual([]);
    expect(component.departamentos).toEqual([]);
  });


  it('Unitaria Comprobar Busqueda Departamento Sin exito',()=>{
    component.filtername ='z';
    component.TipoBusqueda = 'AllDepa';
    let arrayDept=[{'id':1,'departamento':'Comida'},{'id':2,'departamento':'Cosmeticos'}]
    component.departamentos=arrayDept;
    component.ArrayContext=arrayDept;
    component.MetodoSeleccionDeBusqueda();
    expect(component.departamentos).toEqual([]);

  });


  it('Unitaria Comprobar Busqueda Departamento Encontrado con exito',()=>{
    component.filtername ='Co';
    component.TipoBusqueda = 'AllDepa';
    let arrayDept=[{'id':1,'departamento':'Comida'},{'id':2,'departamento':'Cosmeticos'},{'id':2,'departamento':'Juguetes'}]
    component.departamentos=arrayDept;
    component.ArrayContext=arrayDept;
    component.MetodoSeleccionDeBusqueda();
    expect(component.departamentos).toHaveSize(2);

  });


  
});
