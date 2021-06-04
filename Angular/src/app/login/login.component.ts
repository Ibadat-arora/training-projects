import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router } from '@angular/router'
import { FormControl, FormGroup, Validators } from '@angular/forms'
import { LoginService } from '../core/service/login.service';
import { LoginRequest } from './login.model';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email: string = "";
  password: string = "";

  constructor(private activatedRoute:ActivatedRoute,private router: Router,private service:LoginService,
    private _snackBar: MatSnackBar) { }
  

  loginForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  })

  ngOnInit(): void {
    this.setSessionFlag();
  }

  validateEmail(): Boolean {
    let isEmailValid: Boolean = false;
    let emailFormat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if (this.email.match(emailFormat)) {
      isEmailValid = true;
    }
    return isEmailValid;
  }

  setSessionFlag() {
    sessionStorage.setItem("setSessionFlag", "false");
  }

  authenticatePost(){
    if (this.validateEmail() != true) {
      this._snackBar.open("Incorrect email format","ok");
    } else {
      let loginRequest : LoginRequest = {};
      loginRequest.email = this.email ;
      loginRequest.password = this.password ;
      this.service.authenticate(loginRequest).subscribe(response =>{
          if(response == true ){
            sessionStorage.setItem("setSessionFlag", "true");
            
            this.router.navigate(['/home']);
          }else{
            this._snackBar.open("Invalid credentials","ok");
          } 
        } );
    }
  }
}