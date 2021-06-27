import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Country } from 'src/app/model/country';
import { CountryService } from 'src/app/service/country.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.css']
})
export class HomeScreenComponent implements OnInit {

  constructor(private countryService:CountryService,private imageService:ImageService,private domSanitizer:DomSanitizer,private router:Router) { }
  countries:Country[]=[];
  ngOnInit(): void {
    this.countryService.getRandomCountries().subscribe(response=>{
      this.countries=response;
     console.log(response);
      this.countries.forEach(country=>{
        this.imageService.openImagePath(country.imagePath).subscribe(data=>{
          const blob = new Blob([data], { type: "image/png" });
          const url = URL.createObjectURL(blob);  
          country.image=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
         
        },
        err=>{
          country.image="/assets/images/travelssite1.jpg";
         
        });
      })
    });
  }
  openThis(countryId){
    this.router.navigate(['/cities', {id:countryId}]);
  }

}
