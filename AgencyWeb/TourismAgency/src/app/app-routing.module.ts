import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeScreenComponent } from './components/home-screen/home-screen.component';
import { ListCitiesComponent } from './components/list-cities/list-cities.component';
import { ListCountriesComponent } from './components/list-countries/list-countries.component';
import { ListHotelRoomsComponent } from './components/list-hotel-rooms/list-hotel-rooms.component';
import { ListHotelsComponent } from './components/list-hotels/list-hotels.component';
import { ListRoomsComponent } from './components/list-rooms/list-rooms.component';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { UserInfoPageComponent } from './components/user-info-page/user-info-page.component';
import { RouteGuardService } from './service/route-guard.service';

const routes: Routes = [
  {path: '*',component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'sign-up', component:SignUpComponent},
  {path: 'home', component: HomeScreenComponent,},
  {path: 'countries',component:ListCountriesComponent,},
  {path: 'cities', component:ListCitiesComponent,},
  {path: 'hotels', component:ListHotelsComponent, },
  {path: 'rooms', component:ListRoomsComponent, canActivate:[RouteGuardService]},
  {path: 'hotelRooms',component:ListHotelRoomsComponent, },
  {path: 'userProfile',component:UserInfoPageComponent, canActivate:[RouteGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
