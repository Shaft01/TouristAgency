import { Component, OnInit } from '@angular/core';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { HotelRoomService } from 'src/app/service/hotel-room.service';

@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.css']
})
export class HomeScreenComponent implements OnInit {

  constructor(private hotelRoomService:HotelRoomService) { }
  hotelRooms:HotelRoom[]=[];
  ngOnInit(): void {
    this.hotelRoomService.getHotelRooms().subscribe(response=>{
      this.hotelRooms=response;
      console.log(this.hotelRooms);
    });
  }

}
