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
    ListHotelsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS, 
    useClass: HttpIntercepterBasicAuthService,
    multi: true
   }],
  bootstrap: [AppComponent]
})
export class AppModule { }
