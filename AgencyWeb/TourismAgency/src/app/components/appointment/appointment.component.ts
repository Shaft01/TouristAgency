import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Arrangement } from 'src/app/model/arrangement';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { UserRequest } from 'src/app/model/userRequest';
import { ArrangementService } from 'src/app/service/arrangement.service';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';
import { UserService } from 'src/app/service/userService';
import { textChangeRangeIsUnchanged } from 'typescript';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {
  arrangement:Arrangement=new Arrangement();
  constructor(public activeModal: NgbActiveModal,private authentification:BasicAuthenticationService,
    private arrangementService:ArrangementService,private userService:UserService) { }
  numberOfPeople:number;
  numbers:number[]=[1,2,3,4];
  @Input() hotelRoom:HotelRoom;
  user:any;
  ngOnInit(): void {
  
    console.log(this.authentification.getAuthenticatedUser());
    this.userService.getUserByUsername(this.authentification.getAuthenticatedUser()).subscribe(response=>{
      this.user=response;
      
    })
    
  }

  saveArrangement(){
    this.arrangement.hotelRoomId=this.hotelRoom.id;
    
    console.log(this.arrangement.startDate);
    
    this.arrangement.userId=this.user.id;
    
    this.arrangement.price=this.hotelRoom.pricePerPerson * this.numberOfPeople;
    this.arrangementService.saveArrangement(this.arrangement).subscribe(respose=>{
      console.log(respose);
      this.activeModal.close();
    })

  }
}
