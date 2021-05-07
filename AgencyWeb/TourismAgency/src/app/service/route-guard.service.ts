import { Injectable } from '@angular/core';import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { BasicAuthenticationService } from './basic-authentication.service';
unescape

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate{


  constructor(private basicAuthentiactionService: BasicAuthenticationService, private router:Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){

    if(this.basicAuthentiactionService.isUserLoggedIn())
      return true;
    
    this.router.navigate(['login'])
      
    return false;
  }
}
