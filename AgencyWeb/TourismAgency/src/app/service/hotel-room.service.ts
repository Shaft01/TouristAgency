import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JPA_API_URL } from "../constants";
import { HotelRoom } from "../model/hotelRoom";

@Injectable({
    providedIn: 'root'
  })
  export class HotelRoomService{
    constructor(private http:HttpClient){}
  
    saveHotelRoom(hotelRoom:HotelRoom){
        return this.http.post<HotelRoom>(`${JPA_API_URL}/hotelRoom`,hotelRoom);
    }
}