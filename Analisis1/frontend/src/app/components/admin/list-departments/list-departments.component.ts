import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {ToastrService} from 'ngx-toastr';

import { DepartmentAdmin } from "../../../models/department-admin";
import { DepartmentService } from "../../../services/department_admin/department.service";

@Component({
  selector: 'app-list-departments',
  templateUrl: './list-departments.component.html',
  styleUrls: ['./list-departments.component.css']
})
export class ListDepartmentsComponent implements OnInit {

  public departments: any = [];
  constructor(private toastr:ToastrService, private department_service: DepartmentService, private router:Router) { }

  ngOnInit(): void {
    this.getDepartments();
  }

  getDepartments(){
    this.department_service.getDepartments()
    .subscribe(
      (res) => {
        this.departments = res;
        console.log(res);
      },
      err => {
        this.toastr.error("Error", err.error.msg);
        console.log(err);
      }
    )
  }

  editDepartment(department: any) {
    localStorage.setItem("idDepartment", department.id.toString());
    localStorage.setItem("nameDepartment", department.departamento);
    console.log(department.departamento);
    this.router.navigate(['/admin/department/modify']);
  }
}

