import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public basicAuthentificationService:BasicAuthenticationService,private router:Router) { }

  ngOnInit(): void {
  }
  logout() {
     this.basicAuthentificationService.logout();
     this.router.navigate(['/login']);
  }

}
