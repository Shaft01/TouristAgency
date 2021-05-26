import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JPA_API_URL } from "../constants";

@Injectable({
    providedIn: 'root'
  })
  export class WikiService{
    constructor(private http:HttpClient){}

    getInformation(title){
        return this.http.get<any>(`${JPA_API_URL}/wiki?title=${title}`);
    }
  }