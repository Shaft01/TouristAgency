import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Arrangement } from 'src/app/model/arrangement';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {
  arrangement:Arrangement=new Arrangement();
  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  saveArrangement(){

  }
}
