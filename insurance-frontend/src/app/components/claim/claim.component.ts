import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, map } from 'rxjs';
import { Claim } from 'src/app/entities/Claim';
import { Vehicle } from 'src/app/entities/Vehicle';
import { AuthService } from 'src/app/services/auth.service';
import { ClaimService } from 'src/app/services/claim.service';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-claim',
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.css']
})
export class ClaimComponent {
  claimId!: any;
  claim!: Claim;
  vehicle!: Vehicle;

  fileInfos?: Observable<any>;

  constructor(private fileService: FileService, private http:HttpClient, private authenticationService: AuthService, private claimService: ClaimService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.claimId = params.get('claimId');
    });
    this.getClaimInfo();  
    // this.isDev = this.authService.isAuthenticatedDev();
    // alert(this.isDev);
    this.fileInfos = this.fileService.getFiles();
    this.fileInfos = this.fileInfos.pipe(
    map(files => files.filter((file: { claimId: any; }) => file.claimId == this.claimId))
  );
  }

  async getClaimInfo() {

    this.claimService.getClaimById(this.claimId).subscribe((claim: Claim) => {
      this.claim = claim;
      console.log(claim);
    });
        
  }

  navigateToUpdateClaim(claimId: any) {
    this.router.navigate(['/updateclaim', claimId]);
  }

  withdrawClaim(claimId: any) {
    this.claim.claimStatus = {'id':7,'name':"Withdrawn"};
    this.claimService.updateClaim(this.claim).subscribe((claim) => {
      this.claim = claim;
      this.claimId = claim.claimId;
      this.vehicle = claim.vehicle;    
    });
    console.log(this.claim);
    this.router.navigate(['/claim', claimId]);
  }

  showAdjusterFeature(): boolean {
    return this.authenticationService.isAuthenticatedAdjuster();
  }

  approveClaim() {
    this.claim.claimStatus = {'id':3,'name':"Approved"};
    this.claimService.updateClaim(this.claim).subscribe((claim) => {
      this.claim = claim;
      this.claimId = claim.claimId;
      this.vehicle = claim.vehicle;    
    });
    console.log(this.claim);
    this.router.navigate(['/claim', this.claimId]);
  }

  denyClaim() {
    this.claim.claimStatus = {'id':4,'name':"Denied"};
    this.claimService.updateClaim(this.claim).subscribe((claim) => {
      this.claim = claim;
      this.claimId = claim.claimId;
      this.vehicle = claim.vehicle;    
    });
    console.log(this.claim);
    this.router.navigate(['/claim', this.claimId]);
  }

  returnClaim() {
    this.claim.claimStatus = {'id':5,'name':"Returned to Client"};
    this.claimService.updateClaim(this.claim).subscribe((claim) => {
      this.claim = claim;
      this.claimId = claim.claimId;
      this.vehicle = claim.vehicle;    
    });
    console.log(this.claim);
    this.router.navigate(['/claim', this.claimId]);
  }

  addNotes() {
    this.router.navigate(['/updateclaim', this.claimId]);
  }

  showAdjusterOptions(){
    if (this.claim.claimStatus.id == 1 || this.claim.claimStatus.id == 2 || this.claim.claimStatus.id == 6) {
      return true;
    } else {
      return false;
    }
  }

  showUserOptions(){
    if (this.claim.claimStatus.id == 1 || this.claim.claimStatus.id == 5) {
      return true;
    } else {
      return false;
    }
  }
}
