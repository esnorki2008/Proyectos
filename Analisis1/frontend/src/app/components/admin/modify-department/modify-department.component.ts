import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {ToastrService} from 'ngx-toastr';

import { DepartmentAdmin } from "../../../models/department-admin";
import { DepartmentService } from "../../../services/department_admin/department.service";

@Component({
  selector: 'app-modify-department',
  templateUrl: './modify-department.component.html',
  styleUrls: ['./modify-department.component.css']
})
export class ModifyDepartmentComponent implements OnInit {
  public name_department: string = "";
  public previous_name:string = "";

  public department: DepartmentAdmin = {
    id: -1,
    nombre: ""
  }
  constructor(private toastr:ToastrService, private department_service: DepartmentService, private router: Router) { }

  ngOnInit(): void {
    let value:any = localStorage.getItem("nameDepartment");
    this.previous_name = value;
  }

  modifyDepartment(){
    if(localStorage.getItem("idDepartment") != undefined && this.name_department != ""){
      let id_D:any = localStorage.getItem("idDepartment");
      let name_D:any = this.name_department;
      this.department.id = Number(id_D);
      this.department.nombre = name_D;
      this.department_service.postEditDepartment(this.department)
      .subscribe(
        (res) => {
          this.toastr.success("Actualización de departamento exitosa", "Datos guardados correctamente")
          console.log(res);
          console.log(this.department)
          this.router.navigate(['/admin/department/list']);
        },
        err => {
          this.toastr.error("Error", err.error.msg);
          console.log(err);
        }
      )
    }
    else {
      this.toastr.error("Datos no encontrados", "Error");
    }
    
    
  }
}
