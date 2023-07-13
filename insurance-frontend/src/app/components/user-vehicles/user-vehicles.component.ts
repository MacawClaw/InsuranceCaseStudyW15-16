import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Vehicle } from 'src/app/entities/Vehicle';
import { AuthService } from 'src/app/services/auth.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-user-vehicles',
  templateUrl: './user-vehicles.component.html',
  styleUrls: ['./user-vehicles.component.css']
})
export class UserVehiclesComponent {

  username!: string;
  vehicles: Vehicle[] = []; 
  isAdjuster:boolean = false;

  constructor(private http:HttpClient, private authenticationService: AuthService, private vehicleService: VehicleService, private router: Router) { }

  ngOnInit() {    
    this.loadVehicles();   
    // this.isDev = this.authService.isAuthenticatedDev();
    // alert(this.isDev);
  }

  async loadVehicles() {

    if (!this.showAdjusterFeature()){
      this.username = localStorage.getItem('currentUser')!;
      this.vehicleService.getVehiclesListByUsername(this.username).subscribe((vehicles) => {
        this.vehicles = vehicles;   
      });
    }
        
  }

  searchVehicles() {
    this.vehicleService.getVehiclesListByUsername(this.username).subscribe((vehicles) => {
    this.vehicles = vehicles;    
    });
        
  }

  navigateToVehicle(vehicleVin: string) {
    this.router.navigate(['/vehicle', vehicleVin]);
  }

  showAdjusterFeature(): boolean {
    return this.authenticationService.isAuthenticatedAdjuster();
  }

}
