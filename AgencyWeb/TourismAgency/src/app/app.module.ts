import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeScreenComponent } from './components/home-screen/home-screen.component';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { ListCountriesComponent } from './components/list-countries/list-countries.component';
import { HttpIntercepterBasicAuthService } from './service/http/http-intercepter-basic-auth.service';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CountryComponent } from './components/country/country.component';
import { CitiesComponent } from './components/cities/cities.component';
import { ListCitiesComponent } from './components/list-cities/list-cities.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { ListHotelsComponent } from './components/list-hotels/list-hotels.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RoomComponent } from './components/room/room.component';
import { ListRoomsComponent } from './components/list-rooms/list-rooms.component';
import { HotelRoomComponent } from './components/hotel-room/hotel-room.component';
import { ListHotelRoomsComponent } from './components/list-hotel-rooms/list-hotel-rooms.component';
import { AppointmentComponent } from './components/appointment/appointment.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeScreenComponent,
    LoginComponent,
    SignUpComponent,
    ListCountriesComponent,
    NavbarComponent,
    CountryComponent,
    CitiesComponent,
    ListCitiesComponent,
    HotelComponent,
    ListHotelsComponent,
    RoomComponent,
    ListRoomsComponent,
    HotelRoomComponent,
    ListHotelRoomsComponent,
    AppointmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    
    BrowserAnimationsModule,
    
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS, 
    useClass: HttpIntercepterBasicAuthService,
    multi: true
   }],
  bootstrap: [AppComponent]
})
export class AppModule { }
