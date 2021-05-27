import { Component, OnInit } from '@angular/core';
import { Arrangement } from 'src/app/model/arrangement';
import { UserRequest } from 'src/app/model/userRequest';
import { ArrangementService } from 'src/app/service/arrangement.service';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';
import { UserService } from 'src/app/service/userService';

@Component({
  selector: 'app-user-info-page',
  templateUrl: './user-info-page.component.html',
  styleUrls: ['./user-info-page.component.css']
})
export class UserInfoPageComponent implements OnInit {
  user:UserRequest=new UserRequest();
  arrangements:Arrangement[]=[];
  constructor(private userService:UserService,private basicAuthentificationService:BasicAuthenticationService,
    private arrangementService:ArrangementService) { }

  ngOnInit(): void {
    this.userService.getUserByUsername(this.basicAuthentificationService.getAuthenticatedUser()).subscribe(response=>{
      this.user=response;
      return this.arrangementService.getAllArrangementsByUser(this.user.username).subscribe(data=>{
        this.arrangements=data;
      });
    });
  }
  cancelReservation(arrangement:Arrangement){
    this.arrangementService.removeArrangement(arrangement.id).subscribe(response=>{
      return this.arrangementService.getAllArrangementsByUser(this.user.username).subscribe(data=>{
        this.arrangements=data;
      });
    });
  }
  checkCancelReservation(arangement:Arrangement){
   let today=new Date();
   let arrangementDate=new Date(arangement.startDate);
   if(arrangementDate.getTime()>today.getTime()){
     return true;
   }else{
     
     return false;
   }
  }
}
