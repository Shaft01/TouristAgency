import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Hotel } from 'src/app/model/hotel';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-upload-gallery',
  templateUrl: './upload-gallery.component.html',
  styleUrls: ['./upload-gallery.component.css']
})
export class UploadGalleryComponent implements OnInit {

  @Input() hotel:Hotel=new Hotel();
  @Output() hotelRoomChange=new EventEmitter<HotelRoom>();
  type="Image";
  imageUpload:File = null;
  constructor(private imageService:ImageService,public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  
   
  }
  saveImage(){

    const formData = new FormData();
    formData.append('image',this.imageUpload);
      if(this.imageUpload!=null){
        return this.imageService.uploadImage(this.hotel.id,this.type,formData).subscribe(response=>{
          this.activeModal.close();
      });
    }
    
    
  }
  handleFileInput(event){
    this.imageUpload = event.target.files[0];
    
  }

}
