import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { RegisterComponent } from './components/register/register.component';
import { UserNavbarComponent } from './components/user-navbar/user-navbar.component';
import { CreateClaimComponent } from './components/create-claim/create-claim.component';
import { UserHomepageComponent } from './components/user-homepage/user-homepage.component';
import { UserVehiclesComponent } from './components/user-vehicles/user-vehicles.component';
import { VehicleComponent } from './components/vehicle/vehicle.component';
import { AddVehicleComponent } from './components/add-vehicle/add-vehicle.component';
import { ClaimsListComponent } from './components/claims-list/claims-list.component';
import { ClaimComponent } from './components/claim/claim.component';
import { UpdateClaimComponent } from './components/update-claim/update-claim.component';
import { CreateClaimByVehicleComponent } from './components/create-claim-by-vehicle/create-claim-by-vehicle.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginComponent,
    NavbarComponent,
    FooterComponent,
    RegisterComponent,
    UserNavbarComponent,
    CreateClaimComponent,
    UserHomepageComponent,
    UserVehiclesComponent,
    VehicleComponent,
    AddVehicleComponent,
    ClaimsListComponent,
    ClaimComponent,
    UpdateClaimComponent,
    CreateClaimByVehicleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
