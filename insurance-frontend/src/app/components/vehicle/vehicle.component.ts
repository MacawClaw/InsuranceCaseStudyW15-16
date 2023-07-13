import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Claim } from 'src/app/entities/Claim';
import { Status } from 'src/app/entities/Status';
import { Vehicle } from 'src/app/entities/Vehicle';
import { ClaimService } from 'src/app/services/claim.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent {

  vin!: any;
  vehicle!: Vehicle;
  claims: Claim[] = [];

  constructor(private http:HttpClient, private vehicleService: VehicleService, private claimService: ClaimService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.vin = params.get('vin');
    });
    this.getVehicleInfo();  
    // this.isDev = this.authService.isAuthenticatedDev();
    // alert(this.isDev);
  }

  async getVehicleInfo() {

    this.vehicleService.getVehicleByVin(this.vin).subscribe((vehicle: Vehicle) => {
      this.vehicle = vehicle;
      console.log(vehicle);  

      this.claimService.getClaimsByVin(this.vin).subscribe((claims) => {
        claims.reverse();
        this.claims = claims;
        console.log(claims);  
      });
    });
        
  }
  
  navigateToClaim(claimId: any) {
    this.router.navigate(['/claim', claimId]);
  }

  navigateToAddClaim(vin: any) {
    this.router.navigate(['/addclaim', vin]);
  }

}
