import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatTableDataSource} from '@angular/material/table';
import { StoreService } from 'src/app/services/storeService/store.service';
import { ToastrService } from 'ngx-toastr'; 
import { departmentStore } from 'src/app/models/departmentStore';
import { UserSessionService } from 'src/app/services/userSession/user-session.service';
interface departmentElement {
  id: number;
  departamento: string;
}



const ELEMENT_DATA: departmentElement[] = [

];

@Component({
  selector: 'app-view-departments',
  templateUrl: './view-departments.component.html',
  styleUrls: ['./view-departments.component.css']
})
export class ViewDepartmentsComponent implements OnInit {
  data: String ="HOLA"
  showingAdd : boolean = true
  transaction: boolean = false
  constructor(private tiendaService:StoreService,private router: Router,private toastr: ToastrService,
    private sessionService: UserSessionService) {
    console.log(`new - data is ${this.data}`);
  }

  displayedColumns: string[] = ['number', 'name','button'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ngOnInit() {
    this.sessionService.userHref(3);
   this.transaction = false;
   this.showAdd();
  }

  addDepartments(input:number) :void{
    let depto: departmentStore = {idDepartamento:input,idTienda:0};
    this.transaction = true;
    this.tiendaService.addDepartment(depto).subscribe(
      res => {
        this.showAdd()
        this.toastr.info("Departamento Agregado Exitosamente ", 'Aviso');  
        this.transaction = false;
      },
      err => {
        this.toastr.error("Departamento No se puede agregar a la tienda",'Error')
        this.transaction = false;
      }
    )
  }

  removeDepartments(input:number) :void{
    let depto: departmentStore = {idDepartamento:input,idTienda:0};
    this.transaction = true;
    this.tiendaService.removeDepartment(depto).subscribe(
      res => {
        this.showRemove()
        this.toastr.warning("Departamento Eliminado ", 'Aviso');  
        this.transaction = false;
      },
      err => {
        this.transaction = false;
        this.toastr.error("Departamento No se puede Eliminar El Departamento De La Tienda",'Error')
      }
    );
  }
  /*
    Funcion Para Comparar Que Departamentos NO Tiene Una Tienda
  */
  showRemove() : void{
    this.transaction = false;
    this.tiendaService.getDepartment()
      .subscribe(
        res => {
          const departmentArray: departmentElement[] = JSON.parse( JSON.stringify(res)) 
          const departmentData: departmentElement[] = [] 
          this.tiendaService.getAddDepartment()
                .subscribe(
                  resActual => {   
                    const StoredepartmentArray: departmentStore[] = JSON.parse( JSON.stringify(resActual)) 
                    StoredepartmentArray.forEach(
                      function(Added){
                        departmentArray.forEach(
                          function(Department){
                            if(Added.idDepartamento == Department.id){
                              departmentData.push(Department)
                            }
                          }
                        )
                        
                      }             
                    )
                    this.dataSource = new MatTableDataSource(departmentData);
                    this.showingAdd = false;
                    return res;
                  },
                  err => {
                    this.toastr.error("No Se Pudo Obtener Tienda y sus departamentos",'Error')
                  }
                )

          return res;
        },
        err => {
          this.toastr.error("No Se Obtener Lista Departamentos, Revisar Conexion",'Error')
        }
      )
     
  }
  /*
    Funcion Para Comparar Que Departamentos  Tiene Una Tienda
  */
  showAdd() : void{
    this.transaction = false;
    this.tiendaService.getDepartment()
      .subscribe(
        res => {
          const departmentArray: departmentElement[] = JSON.parse( JSON.stringify(res)) 
          const departmentData: departmentElement[] = [] 
          this.tiendaService.getAddDepartment()
                .subscribe(
                  resActual => {   
                    const StoredepartmentArray: departmentStore[] = JSON.parse( JSON.stringify(resActual)) 
                    departmentArray.forEach(
                      function(Department){
                        let notADepartment = false;
                        StoredepartmentArray.forEach(
                          function(Added){
                            if(Added.idDepartamento == Department.id){
                              notADepartment =true;
                            }
                          }
                        )
                        if(!notADepartment)
                          departmentData.push(Department) 
                      }             
                    )
                    this.dataSource = new MatTableDataSource(departmentData);
                    this.showingAdd = true;
                    return res;
                  },
                  err => {
                    this.toastr.error("No Se Pudo Obtener Tienda y sus departamentos",'Error')
                  }
                )

          return res;
        },
        err => {
          this.toastr.error("No Se Obtener Lista Departamentos, Revisar Conexion",'Error')
        }
      )
      
  }
  hrefDepartments() :void{
    this.router.navigate(['merchant']);
  }
}
