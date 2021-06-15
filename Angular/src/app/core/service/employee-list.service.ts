import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../model/employee.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeListService {

  constructor(private httpClient: HttpClient) { }

  searchByFirstName(firstName : string):Observable<Employee[]> {
    let url = '/employee?firstName=' + firstName ;
        return this.httpClient.get<Employee[]>(url) ;
  }

  searchByLastName(lastName : string):Observable<Employee[]> {
    let url = '/employee?lastName=' + lastName;
    return this.httpClient.get<Employee[]>(url) ;
  }
}
