import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms'
import { jobProfile } from '../jobProfile.component'
import { ActivatedRoute, Router } from '@angular/router'
import { EmployeeService } from '../core/service/employee.service';
import { Employee } from '../core/model/employee.model';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  
  jobProfiles: jobProfile[] = [
    { jobId: 1, jobName: "Developer" },
    { jobId: 2, jobName: "Tester" },
    { jobId: 3, jobName: "Product Manager" },
    { jobId: 4, jobName: "HR" }
  ];

  employee: Employee = new Employee ;

  constructor(private activatedRoute: ActivatedRoute, 
    private router: Router, 
    private employeeService: EmployeeService,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.validateSessionStorage();
  }

  autoFillUserValues() { 
    this._snackBar.open("values will autofill","ok");
    this.employeeService.getEmployeeById(2).subscribe((response : Employee)=> {
      this.employee = response ;
    });
  }

  validateSessionStorage() {
    if (sessionStorage.getItem("setSessionFlag") == "false") {
      this.redirectToLoginPage();
    } else {
      this.autoFillUserValues();
    }
  }

  redirectToLoginPage() {
    this.router.navigate(['/login']);;
  }

  updateEmployeeData(){
    this.employeeService.updateEmployeeData(this.employee).subscribe(response =>{
      if(response == true){
        this._snackBar.open("Data has been submitted","ok");
      }else{
        this._snackBar.open("Data not submitted","ok");
      }
    }
      ) ;
  }
}
