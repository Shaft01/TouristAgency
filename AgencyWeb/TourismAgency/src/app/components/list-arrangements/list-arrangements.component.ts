import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Arrangement } from 'src/app/model/arrangement';
import { Hotel } from 'src/app/model/hotel';
import { ArrangementService } from 'src/app/service/arrangement.service';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';

@Component({
  selector: 'app-list-arrangements',
  templateUrl: './list-arrangements.component.html',
  styleUrls: ['./list-arrangements.component.css']
})
export class ListArrangementsComponent implements OnInit {
@Input() hotel:Hotel=new Hotel();
arrangements:Arrangement[]=[];
  constructor(public activeModal: NgbActiveModal,private basicAuthentificationService:BasicAuthenticationService,private arrangementService:ArrangementService) { }

  ngOnInit(): void {
    this.arrangementService.getAllArrangementsByUserAndHotel(this.basicAuthentificationService.getAuthenticatedUser(),this.hotel.id).subscribe(response=>{
      this.arrangements=response;
    })
  }

}
