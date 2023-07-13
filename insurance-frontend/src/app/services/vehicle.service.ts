import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Vehicle } from '../entities/Vehicle';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private vehicleApi = 'http://localhost:8081/api/vehicles';
  
  private httpOptions  = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
  };
  constructor(private http:HttpClient) { }

  getVehiclesListByUsername(username: any) {
    const url = `${this.vehicleApi}/users/${username}`;
    return this.http.get<Vehicle[]>(url, this.httpOptions);
  }

  addVehicle(vehicle: Vehicle): Observable<Vehicle>{
    return this.http.post<Vehicle>(this.vehicleApi, vehicle, this.httpOptions);
  }
  
  getAllVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.vehicleApi, this.httpOptions);
  }

  getVehicleByVin(vin: string): Observable<Vehicle> {
    const url = `${this.vehicleApi}/${vin as string}`;
    return this.http.get<Vehicle>(url, this.httpOptions);
  }
}
