import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserRequest } from 'src/app/model/userRequest';
import { UserService } from 'src/app/service/userService';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private userService:UserService, private router:Router) { }
  user:UserRequest = new UserRequest();
  newPassword:string;
  showPassword:boolean = true;
  invalidForm:boolean = false; 
  public message: boolean = false;
  public messagePC: boolean = false;
  public pcSuccess: boolean = false;
  ngOnInit(): void {
  }

  saveUser(){
    console.log(this.user.username);
    if (this.user.password === this.user.newPassword){
      this.user.authorityName = "ROLE_USER";
      this.userService.createUser(this.user).subscribe(response=>{
        console.log(response);
        this.router.navigate(['login']);
      },
      error=>{
        this.invalidForm=true;
      });
    }else{
      this.message = true;
    }
  }
}
