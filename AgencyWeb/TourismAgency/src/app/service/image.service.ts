import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JPA_API_URL } from "../constants";
import { Image } from "../model/image";

@Injectable({
    providedIn: 'root'
  })
  export class ImageService{
    constructor(private http:HttpClient){}

    uploadImage(hotelRoomId,type,image){
      console.log("uploading...");
      let headers=new HttpHeaders();
      console.log(image);
      headers.append("Content-type", "multipart/form-data");
      headers.append("Accept", "application/json");
      return this.http.post<Image>(`${JPA_API_URL}/image/${hotelRoomId}?type=${type}`,image,{ headers:headers}); 
    }
    getImageByHotel(id){
      return this.http.get<Image[]>(`${JPA_API_URL}/image/get-images-by-hotel/${id}`);
    }
    openImage(id){
      return this.http.get<any>(`${JPA_API_URL}/image/${id}`,{ responseType: 'blob' as 'json' });
    }
    openImagePath(imagePath: string) {
      return this.http.get<any>(`${JPA_API_URL}/image/open-image?path=${imagePath}`, {responseType: 'blob' as 'json' });
    }
  }