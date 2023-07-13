import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Claim } from 'src/app/entities/Claim';
import { Vehicle } from 'src/app/entities/Vehicle';
import { AuthService } from 'src/app/services/auth.service';
import { ClaimService } from 'src/app/services/claim.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-create-claim-by-vehicle',
  templateUrl: './create-claim-by-vehicle.component.html',
  styleUrls: ['./create-claim-by-vehicle.component.css']
})
export class CreateClaimByVehicleComponent {
  username!: string;
  vehicle!: Vehicle;
  vin!: string;
  adjusterNotes = "";
  claimCost!: number;
  claimDescription!: string;
  
  constructor(private http: HttpClient, private authenticationService: AuthService, private claimService: ClaimService, private vehicleService: VehicleService, private router: Router, private route: ActivatedRoute){
  }

  ngOnInit(): void{
    this.route.paramMap.subscribe(params => {
      this.vin = params.get('vin')!;
    });
    this.loadVehicle();
  }

  async loadVehicle() {

    if (!this.showAdjusterFeature()){
      this.username = localStorage.getItem('currentUser')!;
      this.vehicleService.getVehicleByVin(this.vin).subscribe((vehicle) => {
        this.vehicle = vehicle;   
      });
    } else {
      this.vehicleService.getVehicleByVin(this.vin).subscribe((vehicle) => {
        this.vehicle = vehicle;
        this.username = this.vehicle.user.username;   
      });
      
    }
        
  }

  onSubmit(){
    if(!this.claimCost){
      alert('Please add estimated cost!');
      return;
    } 
    if (!this.claimDescription){
      alert('Please add claim description!');
      return;
    }
    if (!this.vehicle){
      alert('Please select a vehicle!');
      return;
    }
    
    const newClaim : Claim = {
      claimId: 0,
      user: this.vehicle.user,
      claimStatus: {'id':1,'name': "Filed"},
      vehicle: this.vehicle,
      adjusterNotes: this.adjusterNotes,
      claimCost: this.claimCost,
      claimDescription: this.claimDescription
    }

    console.log(newClaim);
    
    this.claimService.addClaim(newClaim).subscribe((claim: Claim) => (console.log(claim.claimId)));
    alert('Claim saved successfully.');
    
  }

  showAdjusterFeature(): boolean {
    return this.authenticationService.isAuthenticatedAdjuster();
  }
}
