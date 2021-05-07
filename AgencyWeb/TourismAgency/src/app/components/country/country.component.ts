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
  constructor(public activeModal: NgbActiveModal,
    private countryService:CountryService) { }
  
  ngOnInit(): void {
  }

  saveCountry(){
    this.countryService.createCountry(this.country).subscribe(response=>{
      this.countryChange.emit(<Country>response);
      this.activeModal.close();
    });

  }
}
