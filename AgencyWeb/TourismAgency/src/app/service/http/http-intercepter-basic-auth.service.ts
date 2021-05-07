import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { BasicAuthenticationService } from '../basic-authentication.service';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError, filter, take, switchMap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class HttpIntercepterBasicAuthService implements HttpInterceptor{

  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);


  constructor(
    private basicAuthenticationService: BasicAuthenticationService,
    private router:Router
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>>{
    console.log("OVDE");
    let basicAuthHeaderString = this.basicAuthenticationService.getAuthenticatedToken();
    let username = this.basicAuthenticationService.getAuthenticatedUser();

    if (basicAuthHeaderString && username) {
      request = this.addToken(request, basicAuthHeaderString);
    }

    return next.handle(request).pipe(catchError(error => {
      if (error instanceof HttpErrorResponse && error.status === 401) {
        return this.handle401Error(request, next);
      }
      else if (error instanceof HttpErrorResponse && error.status === 400 && this.isRefreshing) { 
        this.handle400Refresh();
        return throwError(error);
      } else {
        return throwError(error);
      }
    }));

  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        Authorization: this.basicAuthenticationService.getAuthenticatedToken()
       
      }
    });
  }

  private handle400Refresh(){
    this.basicAuthenticationService.logout();
  }

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {

    if (!this.isRefreshing) {

      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);
      console.log("OVDE1");
      return this.basicAuthenticationService.refreshToken().pipe(
        switchMap((token: any) => {
          this.isRefreshing = false;
          this.refreshTokenSubject.next(token.jwt);
          return next.handle(this.addToken(request, token.jwt));
        }));
  
     } else {
       return this.refreshTokenSubject.pipe(
         filter(token => token != null),
        take(1),
         switchMap(jwt => {
           return next.handle(this.addToken(request, jwt));
         }));
     }
    }


}
