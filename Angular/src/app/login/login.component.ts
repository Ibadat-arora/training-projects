import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router } from '@angular/router'
import { FormControl, FormGroup, Validators } from '@angular/forms'
import { LoginRequest } from './loginRequest.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import { JwtService } from '../core/service/login.service';
import { LoginResponse } from './loginResponse.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = "";
  password: string = "";

  constructor(private activatedRoute:ActivatedRoute,private router: Router,private jwtService:JwtService,
    private snackBar: MatSnackBar) { }
  

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  })

  ngOnInit(): void {
    this.setSessionFlag();
  }

  validateUsername(): Boolean {
    let isUsernameValid: Boolean = false;
    let emailFormat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if (this.username.match(emailFormat)) {
      isUsernameValid = true;
    }
    return isUsernameValid;
  }

  setSessionFlag() {
    sessionStorage.setItem("setSessionFlag", "false");
    sessionStorage.removeItem("tokenValue") ;
    sessionStorage.removeItem("employeeId");
  }

  authenticatePost(){
    if (this.validateUsername() != true) {
      this.snackBar.open("Incorrect email format","ok");
    } else {
      let loginRequest : LoginRequest = {};
      loginRequest.username = this.username ;
      loginRequest.password = this.password ;

      this.jwtService.login(loginRequest).subscribe((response : LoginResponse)=> {
        let tokenValue : string = response.token ;
        console.log(tokenValue);
        sessionStorage.setItem("setSessionFlag", "true");
        sessionStorage.setItem("tokenValue",tokenValue);
        this.router.navigate(['/employeelist']);
      });
    }
  }
}