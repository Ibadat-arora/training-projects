import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from 'src/app/login/loginRequest.model';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { LoginResponse } from 'src/app/login/loginResponse.model';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private httpClient:HttpClient) { }

   login(loginRequest : LoginRequest ):Observable<LoginResponse>{
    let url = '/authenticate';

    return this.httpClient.post<LoginResponse>(url,loginRequest) ;
  }
}
