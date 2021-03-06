import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JPA_API_URL } from "../constants";
import { HotelRoom } from "../model/hotelRoom";

@Injectable({
    providedIn: 'root'
  })
  export class HotelRoomService{
    constructor(private http:HttpClient){}
    
    getHotelRoomById(id){
      return this.http.get<HotelRoom>(`${JPA_API_URL}/hotelRoom/${id}`);
    }
    saveHotelRoom(hotelRoom:HotelRoom){
        return this.http.post<HotelRoom>(`${JPA_API_URL}/hotelRoom`,hotelRoom);
    }
    getHotelRooms(){
      return this.http.get<HotelRoom[]>(`${JPA_API_URL}/hotelRoom`);
    }
    updateHotelRooms(hotelRoom:HotelRoom){
      return this.http.put<HotelRoom>(`${JPA_API_URL}/hotelRoom/${hotelRoom.id}`,hotelRoom);
    }
    getHotelRoomsByHotel(id){
      return this.http.get<HotelRoom[]>(`${JPA_API_URL}/hotelRoom/get-by-hotel?hotelId=${id}`);
    }
}