import { Component, OnInit } from '@angular/core';
import { Country } from 'src/app/model/country';
import { CountryService } from 'src/app/service/country.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from '../login/login.component';
import { CountryComponent } from '../country/country.component';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { ImageService } from 'src/app/service/image.service';
@Component({
  selector: 'app-list-countries',
  templateUrl: './list-countries.component.html',
  styleUrls: ['./list-countries.component.css']
})
export class ListCountriesComponent implements OnInit {
  countries:Country[]= [];
  constructor(private countryService:CountryService, private modalService: NgbModal,private router:Router,
    private imageService:ImageService, private domSanitizer:DomSanitizer) { }
 
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
      this.countries.forEach(country=>{
        this.imageService.openImagePath(country.imagePath).subscribe(data=>{
          const blob = new Blob([data], { type: "image/png" });
          const url = URL.createObjectURL(blob);  
          country.image=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
         
        },
        err=>{
          console.log("GRESKA");
        });
      })
    });
  }
  openThis(name,event){
    event.stopPropagation();
    //this.router.navigateByUrl("/cities?id="+name);
   
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
