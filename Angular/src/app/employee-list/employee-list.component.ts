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
  username : string = "" ;

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

  Search(){
    if(this.username==""){
      this.getAllEmployees();
    }else{
      this.employeeList = this.employeeList.filter(response => {
        let filterResults : any ;

        if(response.firstName.toLocaleLowerCase().match(this.username.toLocaleLowerCase())){
          filterResults = response.firstName.toLocaleLowerCase().match(this.username.toLocaleLowerCase())
        }else if(response.lastName.toLocaleLowerCase().match(this.username.toLocaleLowerCase())){
          filterResults = response.lastName.toLocaleLowerCase().match(this.username.toLocaleLowerCase())
        }

        return filterResults ;
      })
    }
  }
}
