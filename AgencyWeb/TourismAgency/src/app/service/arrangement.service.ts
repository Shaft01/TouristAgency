import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JPA_API_URL } from "../constants";
import { Arrangement } from "../model/arrangement";

@Injectable({
    providedIn: 'root'
  })
  export class ArrangementService{
   
    
    constructor(private http:HttpClient){}

    saveArrangement(arrangement){
        return this.http.post<Arrangement>(`${JPA_API_URL}/arrangement`,arrangement);
    }
    getAllArrangementsByUser(username){
      return this.http.get<Arrangement[]>(`${JPA_API_URL}/arrangement/get-by-user?username=${username}`);
    }
    getAllArrangementsByUserAndHotel(username:string,hotelId:number){
      return this.http.get<Arrangement[]>(`${JPA_API_URL}/arrangement/get-by-hotel-and-user?username=${username}&hotelId=${hotelId}`);
    }
    removeArrangement(id:number){
      return this.http.delete<Arrangement>(`${JPA_API_URL}/arrangement/${id}`);
    }
}