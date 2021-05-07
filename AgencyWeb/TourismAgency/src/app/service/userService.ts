import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { API_URL } from "../constants";
import { UserRequest } from "../model/userRequest";


@Injectable({
    providedIn: 'root'
  })
  export class UserService {
  
    constructor(private http:HttpClient) { }

    createUser(user:UserRequest){
        return this.http.post(`${API_URL}/auth/signup`,user);
    }
  }