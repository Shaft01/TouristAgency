import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, tap, mapTo, catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { Router } from '@angular/router';
import jwt_decode from "jwt-decode";
import { API_URL } from '../constants';


export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenicaterUser';
export const id = 'id';
export const role = 'role';



@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  private logoutCallbacks = [];

  constructor(private http: HttpClient, private router:Router) { }

   executeJWTAuthenticationBeanService(username, password){
    console.log(username);
    console.log(password); 
    return this.http.post<any>(`${API_URL}/auth/login`, {
       username,
       password
     }).pipe(
       map(
         data=>{
           sessionStorage.setItem(AUTHENTICATED_USER, username);
           sessionStorage.setItem(TOKEN, `Bearer ${data.accessToken}`);
           let decodedToken:any = jwt_decode(data.accessToken);
           sessionStorage.setItem(role, decodedToken.role);
           return data;
         }
       )
     );

   }

  getAuthenticatedUser(){
    return sessionStorage.getItem(AUTHENTICATED_USER);
  }

  getAuthenticatedToken(){
    if(this.getAuthenticatedUser())
      return sessionStorage.getItem(TOKEN);
  }

  getAuthenticatedRole(){
    if(this.getAuthenticatedUser())
      return sessionStorage.getItem(role);
  }

  isUserLoggedIn(){
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

  hasRole(){

    if(this.getAuthenticatedRole()==="ROLE_ADMIN"){
      return true;
     }
     else{
       return  false;
     } 

  }

  logout(){
    sessionStorage.removeItem(AUTHENTICATED_USER)
    sessionStorage.removeItem(TOKEN)
    this.router.navigateByUrl("/login")

    this.logoutCallbacks.forEach(callback => {
      callback();
    });
  }

  addLogoutCallback(callback){
    if(typeof(callback) == "function"){
      this.logoutCallbacks.push(callback);
    }
  }

  refreshToken() {
    return this.http.post<any>(`${API_URL}/auth/refresh`, {
      'token': this.getRefreshToken()
    }).pipe(
      map(
        data=>{
          sessionStorage.setItem(TOKEN, `Bearer ${data.accessToken}`);
          return data;
        }
      ));
  }

  private getRefreshToken() {
    return sessionStorage.getItem(TOKEN);
  }

}

export class AuthenticationBean{
  constructor(public messaage:string){
  }
}
