import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JPA_API_URL } from "../constants";
import { Room } from "../model/room";

@Injectable({
    providedIn: 'root'
  })
  export class RoomService{
    constructor(private http:HttpClient){}
    
    createRoom(room:Room){
        return this.http.post(`${JPA_API_URL}/room`,room);
    }
    getAllRooms(){
        return this.http.get<Room[]>(`${JPA_API_URL}/room`);
    }
    getRoomById(id:number){
        return this.http.get<Room>(`${JPA_API_URL}/room/${id}`);
    }
}