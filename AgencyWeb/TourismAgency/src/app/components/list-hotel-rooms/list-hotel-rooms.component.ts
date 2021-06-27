import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Hotel } from 'src/app/model/hotel';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { Image } from 'src/app/model/image';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';
import { HotelRoomService } from 'src/app/service/hotel-room.service';
import { HotelService } from 'src/app/service/hotel.service';
import { ImageService } from 'src/app/service/image.service';
import { AppointmentComponent } from '../appointment/appointment.component';
import { HotelRoomComponent } from '../hotel-room/hotel-room.component';
import { ListArrangementsComponent } from '../list-arrangements/list-arrangements.component';
import { UploadGalleryComponent } from '../upload-gallery/upload-gallery.component';
export interface slider {
  image: string,
  thumbImage: string,
  title: string
};
@Component({
  selector: 'app-list-hotel-rooms',
  templateUrl: './list-hotel-rooms.component.html',
  styleUrls: ['./list-hotel-rooms.component.css']
})

export class ListHotelRoomsComponent implements OnInit {
  hotelRooms:HotelRoom[]=[];
  
  hotel:Hotel=new Hotel();
  images:Image[]=[];
  param=null;
  selectedIndex: number = 0;

  
  imageObject: Array<Image> = [];
  constructor( private hotelRoomService:HotelRoomService,private imageService:ImageService,
     private modalService: NgbModal,  private domSanitizer: DomSanitizer,private route:ActivatedRoute,
     private hotelService:HotelService,public basicAuthentificationService:BasicAuthenticationService
     ) { }

  ngOnInit(): void {
    this.param = this.route.snapshot.paramMap.get('id');
    console.log(this.param);
    if(this.param!=null){
      this.hotelService.getHotelById(this.param).subscribe(response=>{
        this.hotel=response;
        this.setImageObject();
      })
    }
    this.loadHotelRooms(this.param);
    //this.setImageObject();
    
  }
  setImageObject(){
    this.imageService.getImageByHotel(this.hotel.id).subscribe(response=>{
      this.images=response;
      this.images.forEach(image=>{
        this.imageService.openImage(image.id).subscribe(data=>{
          const blob = new Blob([data], { type: "image/png" });
        
          const url = URL.createObjectURL(blob);  
           image.image=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
           image.thumbImage=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
          this.imageObject.push(image);
          console.log(this.imageObject);
        })
      })
    });
  }
  setPrev() {
    if(this.selectedIndex==0){
      return;
    }
    this.selectedIndex -=1;
 }
 setNext(){
   if(this.selectedIndex==this.images.length-1){
     return;
   }
   this.selectedIndex +=1;
 }
  initializeList(response){
    this.images=response;
    for(let i=0;i<this.images.length;i++){
      this.imageService.openImage(this.images[i].id).subscribe(data=>{
        const blob = new Blob([data], { type: "image/png" });
        const url = URL.createObjectURL(blob);  
       
       
      })
    }
  }
  createArrangement(hotelRoom){
    const modal= this.modalService.open(AppointmentComponent);
    modal.componentInstance.hotelRoom = hotelRoom;
  }
 
  createHotelRoom(){
    const modalRef =this.modalService.open(HotelRoomComponent);
    modalRef.componentInstance.hotel=this.hotel;
    modalRef.componentInstance.hotelRoomChange.subscribe(response=>{
      this.loadHotelRooms(this.param);
    })
  }
  loadHotelRooms(id){
    if(id==null){
    this.hotelRoomService.getHotelRooms().subscribe(response=>{
      this.hotelRooms=response;
    });
  }else{
    this.hotelRoomService.getHotelRoomsByHotel(id).subscribe(response=>{
      this.hotelRooms=response;
    })
  }
  }
  uploadImageToGallery(){
    
      const modalRef =this.modalService.open(UploadGalleryComponent);
      modalRef.componentInstance.hotel=this.hotel;
      modalRef.componentInstance.hotelRoomChange.subscribe(response=>{
        this.loadHotelRooms(this.param);
      })
  }
  userArrangements(){
    const modalRef=this.modalService.open(ListArrangementsComponent);
    modalRef.componentInstance.hotel=this.hotel;
  }
}
