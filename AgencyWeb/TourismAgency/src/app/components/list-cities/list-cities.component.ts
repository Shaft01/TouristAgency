import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { City } from 'src/app/model/city';
import { CityService } from 'src/app/service/city.service';
import { CitiesComponent } from '../cities/cities.component';

@Component({
  selector: 'app-list-cities',
  templateUrl: './list-cities.component.html',
  styleUrls: ['./list-cities.component.css']
})
export class ListCitiesComponent implements OnInit {
  cities:City[]=[];
  constructor(private cityService:CityService,private modalService: NgbModal) { }

  ngOnInit(): void {

    this.loadCities();
  }

  createCity(){
    const modalRef =this.modalService.open(CitiesComponent);
    modalRef.componentInstance.cityChange.subscribe(response=>{
      this.loadCities();
    });
  }
  loadCities(){
    this.cityService.getAllCities().subscribe(response=>{
      this.cities = response;
    });
  }
  openThis(name,event){
    event.stopPropagation();
    console.log(name);
  }
  edit(city,event){
    event.stopPropagation();

    const modalRef =this.modalService.open(CitiesComponent);
    modalRef.componentInstance.city=city;
    modalRef.componentInstance.cityChange.subscribe(response=>{
      this.loadCities();
    });
  }
}
