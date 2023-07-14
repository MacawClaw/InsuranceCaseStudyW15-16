import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Claim } from 'src/app/entities/Claim';
import { AuthService } from 'src/app/services/auth.service';
import { ClaimService } from 'src/app/services/claim.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-claims-list',
  templateUrl: './claims-list.component.html',
  styleUrls: ['./claims-list.component.css']
})
export class ClaimsListComponent {
  
  username = localStorage.getItem('currentUser');
  firstName = localStorage.getItem('currentName')!;
  statuses = [{'id':0,'name': "All"}, 
  {'id':1,'name': "Filed"}, 
  {'id':2,'name': "Being Evaluated"}, 
  {'id':3,'name': "Approved"}, 
  {'id':4,'name': "Denied"}, 
  {'id':5,'name': "Returned to Client"}, 
  {'id':6,'name': "Updated"}, 
  {'id':7,'name': "Withdrawn"}];
  status!:number;
  vin!: any;
  claims: Claim[] = [];

  constructor(private http:HttpClient, private authenticationService: AuthService, private claimService: ClaimService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    if (!this.showAdjusterFeature()) {
      this.loadClaims();
    } else {
      this.loadAllClaims();
    }
    // this.isDev = this.authService.isAuthenticatedDev();
    // alert(this.isDev);
  }

  async loadClaims() { 

    this.claimService.getClaimsByUsername().subscribe((claims) => {
      claims.reverse();
      this.claims = claims;
      console.log(claims);  
    });
        
  }

  async loadAllClaims() { 

    this.claimService.getAllClaims().subscribe((claims) => {
      claims.reverse();
      this.claims = claims;
      console.log(claims);  
    });
        
  }

  loadAllClaimsByStatus(status: number) { 

    this.claimService.getClaimsByStatus(status).subscribe((claims) => {
      claims.reverse();
      this.claims = claims;
      console.log(claims);  
    });
        
  }
  
  navigateToClaim(claimId: any) {
    this.router.navigate(['/claim', claimId]);
  }

  
  showAdjusterFeature(): boolean {
    return this.authenticationService.isAuthenticatedAdjuster();
  }

  updateClaimList(status: number) {
    if (status === 0) {
      this.loadAllClaims();
    } else {
      this.loadAllClaimsByStatus(status);
    }
  }

}
