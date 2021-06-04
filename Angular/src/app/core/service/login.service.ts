import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { LoginRequest } from '../../login/login.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private url = environment.hosturl + '/login';
   
  constructor(private httpClient: HttpClient) { }

  authenticate(loginRequest : LoginRequest){
    return this.httpClient.post(this.url,loginRequest);
  }
}
