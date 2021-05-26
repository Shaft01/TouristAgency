import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Hotel } from 'src/app/model/hotel';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { Room } from 'src/app/model/room';
import { HotelRoomService } from 'src/app/service/hotel-room.service';
import { HotelService } from 'src/app/service/hotel.service';
import { ImageService } from 'src/app/service/image.service';
import { RoomService } from 'src/app/service/room.service';

@Component({
  selector: 'app-hotel-room',
  templateUrl: './hotel-room.component.html',
  styleUrls: ['./hotel-room.component.css']
})
export class HotelRoomComponent implements OnInit {
  @Output() hotelRoomChange = new EventEmitter<HotelRoom>();
  hotelRoom:HotelRoom=new HotelRoom();
  @Input() hotel:Hotel=new Hotel();
  hotels:Hotel[]=[];
  rooms:Room[]=[];
  type;
  imageUpload:File = null;
  constructor(private roomService:RoomService,private hotelService:HotelService
    ,private hotelRoomService:HotelRoomService,
    private imageService:ImageService,public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    this.roomService.getAllRooms().subscribe(response=>{
      this.rooms=response;
      });
    this.hotelService.getAllHotels().subscribe(response=>{
      this.hotels=response;
    });
   
  }
  saveHotelRoom(){
    if(this.hotelRoom.hotelId==null){
      this.hotelRoom.hotelId=this.hotel.id;
    }
    const formData = new FormData();
    formData.append('image',this.imageUpload);
    this.hotelRoomService.saveHotelRoom(this.hotelRoom).subscribe(response=>{
      this.hotelRoomChange.emit(<HotelRoom>response);
      if(this.imageUpload!=null){
        return this.imageService.uploadImage(response.id,this.type,formData).subscribe(response=>{
        
      });
    }
    this.activeModal.close();
    });
  }
  handleFileInput(event){
    this.imageUpload = event.target.files[0];
    
  }

}
