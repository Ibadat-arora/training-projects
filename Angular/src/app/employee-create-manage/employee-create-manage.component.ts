import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms'
import { ActivatedRoute, Router } from '@angular/router'
import { EmployeeService } from '../core/service/employee.service';
import { Employee } from '../core/model/employee.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import { JobProfileService } from '../core/service/job-profile.service';
import { JobProfile } from '../core/jobProfile/jobProfile.model';

@Component({
  selector: 'app-home',
  templateUrl: './employee-create-manage.component.html',
  styleUrls: ['./employee-create-manage.component.css']
})

export class EmployeeCreateManage implements OnInit {
  
  jobProfiles : JobProfile[] = [] ;
  employeeForm : FormGroup = this.formBuilder.group(new Employee(),{formArray : false}) ;
  employeeId : number = 0 ;
  constructor(private activatedRoute: ActivatedRoute, 
    private router: Router, 
    private employeeService: EmployeeService,
    private snackBar: MatSnackBar,
    private formBuilder : FormBuilder,
    private jobProfileService : JobProfileService) {
      this.activatedRoute.params.subscribe(data => {
        this.employeeId = data.id ;
      })
    }

  ngOnInit(): void {
    this.validateSessionStorage();
  }

  get employee() : Employee {
    return this.employeeForm.getRawValue() as Employee ;
  }

  autoFillUserValues() { 
    this.snackBar.open("values will autofill","ok");
    this.employeeService.getEmployeeById(this.employeeId).subscribe((response : Employee)=> {
      this.employeeForm.patchValue(response) ;
    });
  }

  validateSessionStorage() {
    if (sessionStorage.getItem("setSessionFlag") == "false") {
      this.redirectToLoginPage();
    } else {
      this.getAllJobProfiles();
      this.autoFillUserValues();
    }
  }

  redirectToLoginPage() {
    this.router.navigate(['/login']);
  }

  updateEmployeeData(){
    this.employeeService.updateEmployeeData(this.employee).subscribe(response =>{
      if(response == true){
        this.snackBar.open("Data has been submitted","ok");
      }else{
        this.snackBar.open("This role cannot update the data","ok");
      }
    }
      ) ;
  }

  getAllJobProfiles(){
    this.jobProfileService.getAllJobProfiles().subscribe(response =>{
      this.jobProfiles = response ;
    })
  }
}
