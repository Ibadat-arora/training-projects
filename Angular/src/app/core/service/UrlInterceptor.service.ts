import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from 'src/environments/environment'; 


@Injectable()
export class UrlInterceptor implements HttpInterceptor{

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const tokenValue = sessionStorage.getItem("tokenValue") ;

        if(tokenValue) {
           const clonedReq = request.clone({
                headers : request.headers.set("Authorization","Bearer " + tokenValue ) 
            }) ;

            return next.handle(clonedReq);
        }else {
          return next.handle(request);
        }
    }
}