import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Claim } from 'src/app/entities/Claim';
import { Status } from 'src/app/entities/Status';
import { User } from 'src/app/entities/User';
import { Vehicle } from 'src/app/entities/Vehicle';
import { AuthService } from 'src/app/services/auth.service';
import { ClaimService } from 'src/app/services/claim.service';
import { ClientService } from 'src/app/services/client.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-create-claim',
  templateUrl: './create-claim.component.html',
  styleUrls: ['./create-claim.component.css']
})
export class CreateClaimComponent {
  username!: string;
  vehicle!: Vehicle;
  vin!: string;
  vehicles!: Vehicle[];
  adjusterNotes = "";
  claimCost!: number;
  claimDescription!: string;
  claimId!: number;
  
  constructor(private http: HttpClient, private authenticationService: AuthService, private claimService: ClaimService, private vehicleService: VehicleService, private router: Router){
  }

  ngOnInit(): void{
    this.loadVehicles();
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
    
    this.claimService.addClaim(newClaim).subscribe((claim: Claim) => (
      this.claimService.getClaimsByUsername().subscribe((claims) => {
        claims.reverse();
        this.claimId = Math.max(...claims.map(claim => claim.claimId));
        console.log(this.claimId); 
        this.router.navigate(['/managefile', this.claimId]); 
      })
      ));
    alert('Claim saved successfully.');
    
  }

  showAdjusterFeature(): boolean {
    return this.authenticationService.isAuthenticatedAdjuster();
  }
}
