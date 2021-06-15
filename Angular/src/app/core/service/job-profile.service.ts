import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { JobProfile } from '../jobProfile/jobProfile.model';

@Injectable({
  providedIn: 'root'
})
export class JobProfileService {

  constructor(private httpClient: HttpClient) { }

  getAllJobProfiles():Observable<JobProfile[]>{
    let url = '/jobProfile';

    return this.httpClient.get<JobProfile[]>(url);
  }
}
