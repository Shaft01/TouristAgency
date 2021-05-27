import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { City } from 'src/app/model/city';
import { Country } from 'src/app/model/country';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';
import { CityService } from 'src/app/service/city.service';
import { CountryService } from 'src/app/service/country.service';
import { ImageService } from 'src/app/service/image.service';
import { WikiService } from 'src/app/service/wiki.service';
import { CitiesComponent } from '../cities/cities.component';

@Component({
  selector: 'app-list-cities',
  templateUrl: './list-cities.component.html',
  styleUrls: ['./list-cities.component.css']
})
export class ListCitiesComponent implements OnInit {
  cities:City[]=[];
  param=null;
  country:Country=new Country();
  countryInfo:string="";
  term:string;
  constructor(private cityService:CityService,private route:ActivatedRoute,
    private router:Router,private modalService: NgbModal,private imageService:ImageService,
    private domSanitizer:DomSanitizer,private countryService:CountryService,private wikiService:WikiService,
    public basicAuthentificationService:BasicAuthenticationService) { }

  ngOnInit(): void {
    this.param = this.route.snapshot.paramMap.get('id');
    console.log(this.param);
    if(this.param!=null){
      this.countryService.getCountryById(this.param).subscribe(response=>{
        this.country=response;
        return this.wikiService.getInformation(this.country.name).subscribe(data=>{
          this.countryInfo=data["content"];
        })
      })
    }
    this.loadCities(this.param);
  }

  createCity(){
    const modalRef =this.modalService.open(CitiesComponent);
    modalRef.componentInstance.country=this.country;
    modalRef.componentInstance.cityChange.subscribe(response=>{
      this.loadCities(this.param);
    });
  }
  loadCities(id){
    if(id==null){
      this.cityService.getAllCities().subscribe(response=>{
       this.cities = response;
       this.loadCityImages();
       
     });
    }else{
      this.cityService.getCitiesByCountry(id).subscribe(response=>{
        this.cities=response;
        this.loadCityImages();
        // this.cities.forEach(city=>{
        //   if(city.imagePath!=null){
        //     this.imageService.openImagePath(city.imagePath).subscribe(data=>{
        //       const blob = new Blob([data], { type: "image/png" });
        //       const url = URL.createObjectURL(blob);  
        //       city.image=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
           
        //     },
        //     err=>{
        //       console.log("GRESKA");
        //     });
        //   } 
        // })
      });
    }
  }
  loadCityImages(){
    this.cities.forEach(city=>{
      if(city.imagePath!=null){
        this.imageService.openImagePath(city.imagePath).subscribe(data=>{
          const blob = new Blob([data], { type: "image/png" });
          const url = URL.createObjectURL(blob);  
          city.image=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
       
        },
        err=>{
          city.image="/assets/images/travelssite1.jpg";
        });
      }else{
        city.image="/assets/images/travelssite1.jpg";
      } 
    });
  }
  openThis(name,event){
    event.stopPropagation();
    this.router.navigate(['/hotels', {id:name}]);
    console.log(name);
  }
  edit(city,event){
    event.stopPropagation();

    const modalRef =this.modalService.open(CitiesComponent);
    modalRef.componentInstance.city=city;
    modalRef.componentInstance.cityChange.subscribe(response=>{
      this.loadCities(this.param);
    });
  }
  delete(city,event){
    event.stopPropagation();

    this.cityService.deleteCity(city.id).subscribe(response=>{
      this.loadCities(this.param);
      console.log(response);
    })
  }
}
