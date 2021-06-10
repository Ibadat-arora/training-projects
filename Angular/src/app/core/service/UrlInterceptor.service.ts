import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from 'src/environments/environment'; 


@Injectable()
export class UrlInterceptor implements HttpInterceptor{

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const tokenValue = sessionStorage.getItem("tokenValue") ;
        let authorizedRequest : HttpRequest<any> = request ;

        if(tokenValue) {
            authorizedRequest = request.clone({
                headers : request.headers.set("Authorization","Bearer " + tokenValue ) 
            }) ;
        }

        const finalRequest = authorizedRequest.clone({
            url : `${environment.hosturl}` + authorizedRequest.url 
        });

        return next.handle(finalRequest);
    }
}