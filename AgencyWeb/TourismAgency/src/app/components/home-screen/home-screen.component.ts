import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Country } from 'src/app/model/country';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { CountryService } from 'src/app/service/country.service';
import { HotelRoomService } from 'src/app/service/hotel-room.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.css']
})
export class HomeScreenComponent implements OnInit {

  constructor(private countryService:CountryService,private imageService:ImageService,private domSanitizer:DomSanitizer) { }
  countries:Country[]=[];
  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe(response=>{
      this.countries=response;
     
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

}
