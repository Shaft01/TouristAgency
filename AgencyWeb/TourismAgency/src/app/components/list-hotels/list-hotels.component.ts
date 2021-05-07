import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Hotel } from 'src/app/model/hotel';
import { HotelService } from 'src/app/service/hotel.service';
import { HotelComponent } from '../hotel/hotel.component';

@Component({
  selector: 'app-list-hotels',
  templateUrl: './list-hotels.component.html',
  styleUrls: ['./list-hotels.component.css']
})
export class ListHotelsComponent implements OnInit {
  hotels:Hotel[]=[];
  constructor(private modalService: NgbModal,private hotelService:HotelService) { }

  ngOnInit(): void {
    this.loadHotels();
  }

  createHotel(){
    const modalRef =this.modalService.open(HotelComponent);
    modalRef.componentInstance.hotelChange.subscribe(response=>{
      this.loadHotels();
    })
  }
  loadHotels(){
    this.hotelService.getAllHotels().subscribe(response=>{
      this.hotels=response;
    });
  }
}
