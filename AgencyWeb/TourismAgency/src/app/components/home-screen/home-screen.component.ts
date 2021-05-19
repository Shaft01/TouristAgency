import { Component, OnInit } from '@angular/core';
import { Country } from 'src/app/model/country';
import { HotelRoom } from 'src/app/model/hotelRoom';
import { CountryService } from 'src/app/service/country.service';
import { HotelRoomService } from 'src/app/service/hotel-room.service';

@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.css']
})
export class HomeScreenComponent implements OnInit {

  constructor(private countryService:CountryService) { }
  countries:Country[]=[];
  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe(response=>{
      this.countries=response;
      console.log(this.countries);
    });
  }

}
