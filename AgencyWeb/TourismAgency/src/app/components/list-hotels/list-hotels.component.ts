import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Hotel } from 'src/app/model/hotel';
import { HotelService } from 'src/app/service/hotel.service';
import { ImageService } from 'src/app/service/image.service';
import { HotelComponent } from '../hotel/hotel.component';

@Component({
  selector: 'app-list-hotels',
  templateUrl: './list-hotels.component.html',
  styleUrls: ['./list-hotels.component.css']
})
export class ListHotelsComponent implements OnInit {
  hotels:Hotel[]=[];
  constructor(private modalService: NgbModal,private hotelService:HotelService,private route:ActivatedRoute,
    private domSanitizer:DomSanitizer,private imageService:ImageService,private router:Router) { }
  param=null;
  ngOnInit(): void {
    this.param = this.route.snapshot.paramMap.get('id');
    console.log(this.param);
    this.loadHotels(this.param);
  }

  createHotel(){
    const modalRef =this.modalService.open(HotelComponent);
    modalRef.componentInstance.hotelChange.subscribe(response=>{
      this.loadHotels(this.param);
    })
  }
  loadHotels(id){
    if(id==null)
      this.hotelService.getAllHotels().subscribe(response=>{
        this.hotels=response;
      });
      else{
        this.hotelService.getHotelsByCity(id).subscribe(response=>{
          this.hotels=response;
          this.hotels.forEach(hotel=>{
            this.imageService.openImagePath(hotel.imagePath).subscribe(data=>{
              const blob = new Blob([data], { type: "image/png" });
              const url = URL.createObjectURL(blob);  
              hotel.image=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
             
            },
            err=>{
              console.log("GRESKA");
            });
          })
        });
      }
  }
  openThis(name,event){
    event.stopPropagation();
    this.router.navigate(["/hotelRooms",{id:name}]);
    console.log(name);
  }
  edit(hotel:Hotel,event){
    event.stopPropagation();

    const modalRef =this.modalService.open(HotelComponent);
    modalRef.componentInstance.hotel=hotel;
    modalRef.componentInstance.hotelChange.subscribe(response=>{
      this.loadHotels(this.param);
    })
  }
  delete(hotel,event){
    event.stopPropagation();
    this.hotelService.deleteHotel(hotel.id).subscribe(response=>{
      this.loadHotels(this.param);
      console.log(response);
    })
  }
}
