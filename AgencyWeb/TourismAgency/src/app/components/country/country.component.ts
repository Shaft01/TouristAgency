import { Component, OnInit, Output,EventEmitter, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Country } from 'src/app/model/country';
import { CountryService } from 'src/app/service/country.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {
  @Input() country :Country=new Country();
  @Output() countryChange = new EventEmitter<Country>();
  showMessage:boolean=false;
  imageUpload;
  type="Country";
  constructor(public activeModal: NgbActiveModal,
             private countryService:CountryService,
             private imageService:ImageService) { }
  remoteCountries:Country[]=[];
  ngOnInit(): void {
    
   this.countryService.getAllCountriesRemote().subscribe(response=>{
     this.remoteCountries=response;
   });
  }

  saveCountry(){
    const formData = new FormData();
    formData.append('image',this.imageUpload);
    console.log(this.country);
    if(this.country.id==undefined){
    this.countryService.createCountry(this.country).subscribe((response)=>{
      this.countryChange.emit(<Country>response);
      return this.imageService.uploadImage(response.id,this.type,formData).subscribe(response=>{
        this.activeModal.close();
      });

    },
    (error)=>{
     this.showMessage=true;
    });
  }else{
    this.countryService.updateCountry(this.country).subscribe(response=>{
      this.countryChange.emit(<Country>response);
      this.activeModal.close();
    })
  }
}

  handleFileInput(event){
    this.imageUpload = event.target.files[0];
    
  }
}
