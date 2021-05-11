import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeScreenComponent } from './components/home-screen/home-screen.component';
import { ListCitiesComponent } from './components/list-cities/list-cities.component';
import { ListCountriesComponent } from './components/list-countries/list-countries.component';
import { ListHotelsComponent } from './components/list-hotels/list-hotels.component';
import { ListRoomsComponent } from './components/list-rooms/list-rooms.component';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { RouteGuardService } from './service/route-guard.service';

const routes: Routes = [
  {path: '*',component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'sign-up', component:SignUpComponent},
  {path: 'home', component: HomeScreenComponent,canActivate:[RouteGuardService]},
  {path: 'countries',component:ListCountriesComponent,canActivate:[RouteGuardService]},
  {path: 'cities', component:ListCitiesComponent,canActivate:[RouteGuardService]},
  {path: 'hotels', component:ListHotelsComponent, canActivate:[RouteGuardService]},
  {path: 'rooms', component:ListRoomsComponent, canActivate:[RouteGuardService]},
  {path: 'hotelRooms',component:ListHotelsComponent, canActivate:[RouteGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
