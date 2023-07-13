import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CreateClaimComponent } from './components/create-claim/create-claim.component';
import { UserHomepageComponent } from './components/user-homepage/user-homepage.component';
import { UserVehiclesComponent } from './components/user-vehicles/user-vehicles.component';
import { VehicleComponent } from './components/vehicle/vehicle.component';
import { AddVehicleComponent } from './components/add-vehicle/add-vehicle.component';
import { ClaimsListComponent } from './components/claims-list/claims-list.component';
import { ClaimComponent } from './components/claim/claim.component';
import { UpdateClaimComponent } from './components/update-claim/update-claim.component';
import { CreateClaimByVehicleComponent } from './components/create-claim-by-vehicle/create-claim-by-vehicle.component';

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'createclaim', component: CreateClaimComponent},
  {path: 'userhomepage', component: UserHomepageComponent},
  {path: 'vehicles', component: UserVehiclesComponent},
  {path: 'vehicle/:vin', component: VehicleComponent},
  {path: 'addvehicle', component: AddVehicleComponent},
  {path: 'claims', component: ClaimsListComponent},
  {path: 'claim/:claimId', component: ClaimComponent},
  {path: 'updateclaim/:claimId', component: UpdateClaimComponent},
  {path: 'addclaim/:vin', component: CreateClaimByVehicleComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
