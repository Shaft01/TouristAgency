import { Component, OnInit, Output ,EventEmitter, Input} from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { City } from 'src/app/model/city';
import { Hotel } from 'src/app/model/hotel';
import { CityService } from 'src/app/service/city.service';
import { HotelService } from 'src/app/service/hotel.service';
import { ImageService } from 'src/app/service/image.service';


@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {
  @Output() hotelChange = new EventEmitter<Hotel>();
  cities:City[]=[];
  showMessage=false;
  @Input() hotel:Hotel=new Hotel();
  constructor(public activeModal: NgbActiveModal,private cityService:CityService,private hotelService:HotelService,private imageService:ImageService) { }
  imageUpload;
  ngOnInit(): void {
    this.cityService.getAllCities().subscribe(response=>{
      this.cities=response;
    });
  }
  saveHotel(){
    const formData = new FormData();
    formData.append('image',this.imageUpload);
    if(this.hotel.id==undefined){
      this.hotelService.createHotel(this.hotel).subscribe(response=>{
        this.hotelChange.emit(<Hotel>response);
        return this.imageService.uploadImage(response.id,"City",formData).subscribe(response=>{
          this.activeModal.close();
        });
      },
      error=>{
        this.showMessage=true;
      });
   }else{
     this.hotelService.updateHotel(this.hotel).subscribe(response=>{
      this.hotelChange.emit(<Hotel>response);
      this.activeModal.close();
    },
    error=>{
      this.showMessage=true;
     });
   }
  }
  handleFileInput(event){
    this.imageUpload = event.target.files[0];
    
  }

}
