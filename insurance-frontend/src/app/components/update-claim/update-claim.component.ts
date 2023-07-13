import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Claim } from 'src/app/entities/Claim';
import { Status } from 'src/app/entities/Status';
import { Vehicle } from 'src/app/entities/Vehicle';
import { AuthService } from 'src/app/services/auth.service';
import { ClaimService } from 'src/app/services/claim.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-update-claim',
  templateUrl: './update-claim.component.html',
  styleUrls: ['./update-claim.component.css']
})
export class UpdateClaimComponent {

  username = localStorage.getItem('currentUser');
  vehicle!: Vehicle;
  vehicles!: Vehicle[];
  claimCost!: number;
  claimDescription!: string;
  claimId!: any;
  claim!: Claim;
  clientUsername!: string;
  adjusterNotes!: string;
  
  constructor(private http: HttpClient, private authenticationService: AuthService, private claimService: ClaimService, private vehicleService: VehicleService, private router: Router, private route: ActivatedRoute){
    
  }

  ngOnInit(): void{
    this.route.paramMap.subscribe(params => {
      this.claimId = params.get('claimId');
    });
    this.loadClaim();
  }

  loadClaim() {

    this.claimService.getClaimById(this.claimId).subscribe((claim) => {
      this.claim = claim;
      this.claimId = claim.claimId;
      this.vehicle = claim.vehicle;
      this.claimCost = claim.claimCost;
      this.claimDescription = claim.claimDescription;
      this.clientUsername = claim.user.username; 
      this.adjusterNotes = claim.adjusterNotes;   
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

    let newStatus: Status = {'id':0,'name': "All"};

    if (this.showAdjusterFeature()) {
      newStatus = {'id':2,'name': "Being Evaluated"};
    } else {
      newStatus= {'id':6,'name': "Updated"};
    }
    
    const newClaim : Claim = {
      claimId: this.claimId,
      user: this.vehicle.user,
      claimStatus: newStatus,
      vehicle: this.vehicle,
      adjusterNotes: this.adjusterNotes,
      claimCost: this.claimCost,
      claimDescription: this.claimDescription
    }

    console.log(newClaim);
    
    this.claimService.updateClaim(newClaim).subscribe((claim: Claim) => (console.log(claim.claimId)));
    alert('Claim saved successfully.');
    
  }

  showAdjusterFeature(): boolean {
    return this.authenticationService.isAuthenticatedAdjuster();
  }

}
