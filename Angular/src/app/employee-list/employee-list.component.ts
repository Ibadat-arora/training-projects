import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../core/model/employee.model';
import { EmployeeService } from '../core/service/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employeeList : Employee[] = [];

  constructor(private employeeService: EmployeeService,
    private router: Router) { }
 
  ngOnInit(): void {
    this.validateSessionStorage();
  }

  validateSessionStorage() {
    if (sessionStorage.getItem("setSessionFlag") == "false") {
      this.redirectToLoginPage();
    } else {
      this.getAllEmployees();
    }
  }

  redirectToLoginPage() {
    this.router.navigate(['/login']);
  }

  getAllEmployees(){
    this.employeeService.getAllEmployees().subscribe(response =>{
      this.employeeList = response ;
    })
  }

  ViewEmployeeDetails(employeeId : number ){
    this.router.navigate(['/employee-create-manage',employeeId]);
  }
}
