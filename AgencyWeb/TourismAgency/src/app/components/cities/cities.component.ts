import { Component, OnInit, Output,EventEmitter, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { City } from 'src/app/model/city';
import { Country } from 'src/app/model/country';
import { CityService } from 'src/app/service/city.service';
import { CountryService } from 'src/app/service/country.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent implements OnInit {
  @Output() cityChange = new EventEmitter<City>();
  @Input() city:City= new City();
  showMessage=false;
  countries:Country[]=[];
  constructor(public activeModal: NgbActiveModal,private cityService:CityService,private coutryService:CountryService,private imageService:ImageService) { }
  imageUpload;
  ngOnInit(): void {
    this.coutryService.getAllCountries().subscribe(response=>{
      this.countries = response;
    });
  }
  saveCity(){
    const formData = new FormData();
    formData.append('image',this.imageUpload);
    console.log(this.city.id);
    if(this.city.id==undefined){
      console.log("CREATE")
      this.cityService.createCity(this.city).subscribe(response=>{
        this.cityChange.emit(<City>response);
        return this.imageService.uploadImage(response.id,"City",formData).subscribe(response=>{
          this.activeModal.close();
        });
        
      },
      error=>{
        this.showMessage=true;
      })
    }else{
      console.log("UPDATE")
      this.cityService.updateCity(this.city).subscribe(response=>{
        this.cityChange.emit(<City>response);
        this.activeModal.close();
      },
      error=>{
        this.showMessage=true;
      })
    }
  }
  handleFileInput(event){
    this.imageUpload = event.target.files[0];
    
  }


}
