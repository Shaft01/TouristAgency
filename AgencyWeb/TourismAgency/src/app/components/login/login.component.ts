import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = "";
  password = "";
  errorMessage = 'Invalid credentials';
  invalidLogin = false ;
  message = 'Welcome to Virtual Terminal';
  constructor(private router: Router, private basicAuthenticationService: BasicAuthenticationService) { }

  ngOnInit(): void {
   
  }
  handleJWTAuthLogin(){
    this.basicAuthenticationService.executeJWTAuthenticationBeanService(this.username, this.password).subscribe(
      data=>{
        this.router.navigate(['home']);
        this.invalidLogin = false;
      },
      error=>{
        this.invalidLogin = true;
      }
    );

  }
}
