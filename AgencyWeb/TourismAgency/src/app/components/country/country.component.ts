import { Component, OnInit, Output,EventEmitter } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Country } from 'src/app/model/country';
import { CountryService } from 'src/app/service/country.service';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {
  @Output() countryChange = new EventEmitter<Country>();
  country: Country=new Country();
  showMessage:boolean=false;
  constructor(public activeModal: NgbActiveModal,
    private countryService:CountryService) { }
  remoteCountries:Country[]=[];
  ngOnInit(): void {
   this.countryService.getAllCountriesRemote().subscribe(response=>{
     this.remoteCountries=response;
   });
  }

  saveCountry(){
    console.log(this.country);
    this.countryService.createCountry(this.country).subscribe((response)=>{
      this.countryChange.emit(<Country>response);
      this.activeModal.close();
    },
    (error)=>{
     this.showMessage=true;
    });
  }
}
