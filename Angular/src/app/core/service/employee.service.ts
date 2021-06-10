import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Employee } from '../model/employee.model';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})

export class EmployeeService {
    constructor(private httpClient: HttpClient) { }

    getEmployeeById(employeeId: string):Observable<Employee> {
        let url = '/employee/' + employeeId;

        return this.httpClient.get<Employee>(url);
    }

    updateEmployeeData(employee : Employee):Observable<Boolean>{
        let url = '/employee/' +  employee.id ;

        return this.httpClient.put<Boolean>(url,employee);
    }

    getAllEmployees():Observable<Employee[]>{
        let url = '/employee' ;
        return this.httpClient.get<Employee[]>(url) ;
    }
}