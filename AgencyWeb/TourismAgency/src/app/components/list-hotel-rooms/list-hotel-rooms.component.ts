import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { HotelRoomService } from 'src/app/service/hotel-room.service';
import { ImageService } from 'src/app/service/image.service';
import { HotelRoomComponent } from '../hotel-room/hotel-room.component';

@Component({
  selector: 'app-list-hotel-rooms',
  templateUrl: './list-hotel-rooms.component.html',
  styleUrls: ['./list-hotel-rooms.component.css']
})
export class ListHotelRoomsComponent implements OnInit {
  hotelRooms:HotelRoom[]=[];
  constructor( private hotelRoomService:HotelRoomService,
     private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadHotelRooms();
  }
  createHotelRoom(){
    const modalRef =this.modalService.open(HotelRoomComponent);
    modalRef.componentInstance.hotelRoomChange.subscribe(response=>{
      this.loadHotelRooms();
    })
  }
  loadHotelRooms(){
    this.hotelRoomService.getHotelRooms().subscribe(response=>{
      this.hotelRooms=response;
    });
  }
}
