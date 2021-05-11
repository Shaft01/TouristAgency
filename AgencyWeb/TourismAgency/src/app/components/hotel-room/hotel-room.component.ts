import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Hotel } from 'src/app/model/hotel';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { Room } from 'src/app/model/room';
import { HotelRoomService } from 'src/app/service/hotel-room.service';
import { HotelService } from 'src/app/service/hotel.service';
import { RoomService } from 'src/app/service/room.service';

@Component({
  selector: 'app-hotel-room',
  templateUrl: './hotel-room.component.html',
  styleUrls: ['./hotel-room.component.css']
})
export class HotelRoomComponent implements OnInit {
  @Output() hotelRoomChange = new EventEmitter<HotelRoom>();
  hotelRoom:HotelRoom;
  hotels:Hotel[]=[];
  rooms:Room[]=[];
  constructor(private roomService:RoomService,private hotelService:HotelService
    ,private hotelRoomService:HotelRoomService,public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    this.hotelService.getAllHotels().subscribe(response=>{
      this.hotels=response;
    });
    this.roomService.getAllRooms().subscribe(response=>{
      this.rooms=response;
    });
  }
  saveHotelRoom(){
    this.hotelRoomService.saveHotelRoom(this.hotelRoom).subscribe(response=>{
      this.hotelRoomChange.emit(<HotelRoom>response);
      this.activeModal.close();
    })
  }

}
