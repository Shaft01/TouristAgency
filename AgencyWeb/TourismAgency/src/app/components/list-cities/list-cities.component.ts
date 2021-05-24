import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { City } from 'src/app/model/city';
import { CityService } from 'src/app/service/city.service';
import { ImageService } from 'src/app/service/image.service';
import { CitiesComponent } from '../cities/cities.component';

@Component({
  selector: 'app-list-cities',
  templateUrl: './list-cities.component.html',
  styleUrls: ['./list-cities.component.css']
})
export class ListCitiesComponent implements OnInit {
  cities:City[]=[];
  param=null;
  constructor(private cityService:CityService,private route:ActivatedRoute,
    private router:Router,private modalService: NgbModal,private imageService:ImageService,
    private domSanitizer:DomSanitizer) { }

  ngOnInit(): void {
    this.param = this.route.snapshot.paramMap.get('id');
    console.log(this.param);
    this.loadCities(this.param);
  }

  createCity(){
    const modalRef =this.modalService.open(CitiesComponent);
    modalRef.componentInstance.cityChange.subscribe(response=>{
      this.loadCities(this.param);
    });
  }
  loadCities(id){
    if(id==null){
      this.cityService.getAllCities().subscribe(response=>{
       this.cities = response;
     });
    }else{
      this.cityService.getCitiesByCountry(id).subscribe(response=>{
        this.cities=response;
        this.cities.forEach(city=>{
          this.imageService.openImagePath(city.imagePath).subscribe(data=>{
            const blob = new Blob([data], { type: "image/png" });
            const url = URL.createObjectURL(blob);  
            city.image=this.domSanitizer.bypassSecurityTrustResourceUrl(url);
           
          },
          err=>{
            console.log("GRESKA");
          });
        })
      });
    }
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
