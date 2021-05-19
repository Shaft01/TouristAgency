import { Component, OnInit } from '@angular/core';
import { Country } from 'src/app/model/country';
import { CountryService } from 'src/app/service/country.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from '../login/login.component';
import { CountryComponent } from '../country/country.component';
@Component({
  selector: 'app-list-countries',
  templateUrl: './list-countries.component.html',
  styleUrls: ['./list-countries.component.css']
})
export class ListCountriesComponent implements OnInit {
  countries:Country[]= [];
  constructor(private countryService:CountryService, private modalService: NgbModal) { }
 
  ngOnInit(): void {

    this.loadCountries();
  }
  createCountry(){
    const modalRef =this.modalService.open(CountryComponent);
    modalRef.componentInstance.countryChange.subscribe(response=>{
      this.loadCountries();
    })

  }
  loadCountries(){
    this.countryService.getAllCountries().subscribe(response=>{
      this.countries = response;
    });
  }
  openThis(name,event){
    event.stopPropagation();
    
    
  }
  edit(country){
    event.stopPropagation();
    const modalRef = this.modalService.open(CountryComponent);
    modalRef.componentInstance.id=country.id;
    modalRef.componentInstance.countryChange(response=>{
      this.loadCountries();
    });
  }
}
