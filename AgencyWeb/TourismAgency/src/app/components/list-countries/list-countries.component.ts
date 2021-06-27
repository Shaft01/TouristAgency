import { Component, OnInit } from '@angular/core';
import { Country } from 'src/app/model/country';
import { CountryService } from 'src/app/service/country.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CountryComponent } from '../country/country.component';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { ImageService } from 'src/app/service/image.service';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';
@Component({
  selector: 'app-list-countries',
  templateUrl: './list-countries.component.html',
  styleUrls: ['./list-countries.component.css']
})
export class ListCountriesComponent implements OnInit {
  countries:Country[]= [];
  term;
  constructor(private countryService:CountryService, private modalService: NgbModal,private router:Router,
    private imageService:ImageService, private domSanitizer:DomSanitizer, public basicAuthentificationService:BasicAuthenticationService) { }
 
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
      this.loadCountryImages();
    });
  }
  loadCountryImages(){
    this.countries.forEach(country=>{
      if(country.imagePath!=null){
        this.imageService.openImagePath(country.imagePath).subscribe(data=>{
          const blob = new Blob([data], { type: "image/png" });
          const url = URL.createObjectURL(blob);  
          country.image=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
       
        },
        err=>{
          console.log("GRESKA");
          country.image="/assets/images/travelssite1.jpg";
       });
      }else{
        country.image="/assets/images/travelssite1.jpg";
      }
    })
  }
  openThis(name,event){
    event.stopPropagation();
    this.router.navigate(['/cities', {id:name}]);
    
  }
  edit(country,event){
    event.stopPropagation();
    const modalRef = this.modalService.open(CountryComponent);
    modalRef.componentInstance.country=country;
    modalRef.componentInstance.countryChange(response=>{
      this.loadCountries();
    });
  }
  delete(country,event){
    event.stopPropagation();
    this.countryService.deleteCountry(country.id).subscribe(response=>{
      this.loadCountries();
      console.log(response);
    })
  }
}
