import { Component, OnInit } from '@angular/core';
import {ToastrService} from 'ngx-toastr';

import { DepartmentName } from "../../../models/department-admin";
import { DepartmentService } from "../../../services/department_admin/department.service";

@Component({
  selector: 'app-create-department',
  templateUrl: './create-department.component.html',
  styleUrls: ['./create-department.component.css']
})
export class CreateDepartmentComponent implements OnInit {

  public name_department: string = ""; 
  public department: DepartmentName = {
    nombre: ""
  }
  constructor(private toastr: ToastrService, private department_service: DepartmentService) { }

  ngOnInit(): void {
  }

  createDepartment() {
    if (this.name_department != ""){
      this.department.nombre = this.name_department;
      this.department_service.postCreateDepartment(this.department)
      .subscribe(
        (res) => {
          this.toastr.success("Departamento registrado con éxito", "Ingreso exitoso");
          console.log(res);
        },
        err => {
          this.toastr.error("Error", err.error.msg);
          console.log(err);
        }
      )
    }
    else {
      this.toastr.warning("El nombre del departamento aún no se define", "Valor faltante");
    }
  }
}
