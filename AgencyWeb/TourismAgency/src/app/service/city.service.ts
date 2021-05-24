import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JPA_API_URL } from "../constants";
import { City } from "../model/city";

@Injectable({
    providedIn: 'root'
  })
  export class CityService{
    
    constructor(private http:HttpClient){}

    createCity(city:City){
        return this.http.post<City>(`${JPA_API_URL}/city`,city);
    }
    getAllCities(){
        return this.http.get<City[]>(`${JPA_API_URL}/city`);
    }
    getCitiesByCountry(countryId:number){
        return this.http.get<City[]>(`${JPA_API_URL}/city/get-by-country?countryId=${countryId}`);
    }
    updateCity(city: City) {
        return this.http.put<City>(`${JPA_API_URL}/city/${city.id}`,city);
    }
    deleteCity(id){
        return this.http.delete<City>(`${JPA_API_URL}/city/${id}`);
    }
  }