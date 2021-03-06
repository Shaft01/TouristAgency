import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JPA_API_URL } from "../constants";
import { Hotel } from "../model/hotel";

@Injectable({
    providedIn: 'root'
  })
  export class HotelService{
    constructor(private http:HttpClient){}

    getHotelById(id){
        return this.http.get<Hotel>(`${JPA_API_URL}/hotel/${id}`);
    }
    createHotel(hotel:Hotel){
        return this.http.post<Hotel>(`${JPA_API_URL}/hotel`,hotel);
    }
    getAllHotels(){
        return this.http.get<Hotel[]>(`${JPA_API_URL}/hotel`);
    }
    getHotelsByCity(cityId:number){
        return this.http.get<Hotel[]>(`${JPA_API_URL}/hotel/get-by-city?cityId=${cityId}`);
    }
    updateHotel(hotel:Hotel){
        return this.http.put<Hotel>(`${JPA_API_URL}/hotel/${hotel.id}`,hotel);
    }
    deleteHotel(id){
        return this.http.delete<Hotel>(`${JPA_API_URL}/hotel/${id}`);
    }
  }