import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Claim } from '../entities/Claim';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClaimService {

  private claimApi = 'http://localhost:8081/api/claims';
  private httpOptions  = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
  };
  constructor(private http:HttpClient) { }

  addClaim(claim: Claim): Observable<Claim>{
    return this.http.post<Claim>(this.claimApi, claim, this.httpOptions);
  }

  updateClaim(claim: Claim): Observable<Claim>{
    //const urlClaimId = claim.claimId.toString();
    const url = `${this.claimApi}/${claim.claimId as number}`;
    //console.log(urlClaimId);
    //console.log(url);
    return this.http.put<Claim>(url, claim, this.httpOptions);
  }
  
  getAllClaims(): Observable<Claim[]> {
    return this.http.get<Claim[]>(this.claimApi, this.httpOptions);
  }

  getClaimsByUsername(): Observable<Claim[]> {
    const url = `${this.claimApi}/users/${localStorage.getItem('currentUser') as string}`;
    return this.http.get<Claim[]>(url, this.httpOptions);
  }

  getClaimsByVin(vin: string): Observable<Claim[]> {
    const url = `${this.claimApi}/vehicles/${vin as string}`;
    return this.http.get<Claim[]>(url, this.httpOptions);
  }

  getClaimsByStatus(status: number): Observable<Claim[]> {
    const url = `${this.claimApi}/status/${status as number}`;
    return this.http.get<Claim[]>(url, this.httpOptions);
  }

  getClaimById(claimId: number): Observable<Claim> {
    const url = `${this.claimApi}/${claimId as number}`;
    return this.http.get<Claim>(url, this.httpOptions);
  }
}
