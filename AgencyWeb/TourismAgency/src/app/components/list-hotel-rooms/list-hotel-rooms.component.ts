import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { Image } from 'src/app/model/image';
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
  image;
  images:Image[]=[];
  constructor( private hotelRoomService:HotelRoomService,private imageService:ImageService,
     private modalService: NgbModal,  private domSanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.loadHotelRooms();
    this.imageService.getImageByHotelRoom(3).subscribe(response=>{
      this.initializeList(response);
    });
  }
  initializeList(response){
    this.images=response;
    for(let i=0;i<this.images.length;i++){
      this.imageService.openImage(this.images[i].id).subscribe(data=>{
        const blob = new Blob([data], { type: "image/png" });
        const url = URL.createObjectURL(blob);  
        this.image=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
       
      })
    }
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
