import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JPA_API_URL } from "../constants";
import { Country } from "../model/country";

@Injectable({
    providedIn: 'root'
  })
  export class CountryService{
    constructor(private http:HttpClient){}
    
    createCountry(country:Country){
        return this.http.post(`${JPA_API_URL}/country`,country);
    }
    getAllCountries(){
        return this.http.get<Country[]>(`${JPA_API_URL}/country`);
    }
    getCountryById(id:number){
        return this.http.get<Country>(`${JPA_API_URL}/country/${id}`);
    }
    getAllCountriesRemote(){
        return this.http.get<Country[]>(`${JPA_API_URL}/country/get-all-remote`);
    }
    updateCountry(country){
        return this.http.get<Country>(`${JPA_API_URL}/country/${country.id}`,country);
    }
    // getAllCountriesApi(){
    //     return this.http.get<Country[]>(`https://restcountries.eu/rest/v2/all`);
    // }

    
}
  