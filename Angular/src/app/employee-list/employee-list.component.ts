import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../core/model/employee.model';
import { EmployeeListService } from '../core/service/employee-list.service';
import { EmployeeService } from '../core/service/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employeeList : Employee[] = [];
  username : string = "" ;
  employee : Employee = new Employee;

  constructor(private employeeService: EmployeeService,
    private router: Router,
    private employeeListService : EmployeeListService) { }
 
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

  SearchUserName(){
    if(this.username==""){
      this.getAllEmployees();
    }else{
      for(this.employee of (this.employeeList)){
        if(this.employee.firstName.toLocaleLowerCase() == this.username.toLocaleLowerCase()){
          this.employeeListService.searchByFirstName(this.employee.firstName).subscribe(response =>{
            this.employeeList = response ;
          }) ;
        }else if(this.employee.lastName.toLocaleLowerCase() == this.username.toLocaleLowerCase()){
          this.employeeListService.searchByLastName(this.employee.lastName).subscribe(response =>{
            this.employeeList = response ;
          }) ;
        }
      }
    }
  }
}
