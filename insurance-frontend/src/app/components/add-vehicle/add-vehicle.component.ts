import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { User } from 'src/app/entities/User';
import { Vehicle } from 'src/app/entities/Vehicle';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.css']
})
export class AddVehicleComponent {

  username = localStorage.getItem('currentUser')!;
  year!: string;
  make!: string;
  model!: string;
  vin!: string;

  constructor(private http: HttpClient, private vehicleService: VehicleService){
  }

  onSubmit(){
    if(!this.year){
      alert('Please add year!');
      return;
    } 
    if (!this.make){
      alert('Please add make!');
      return;
    }
    if (!this.model){
      alert('Please add model!');
      return;
    }
    if (!this.vin){
      alert('Please add VIN!');
      return;
    }

    const newUser : User = {
      username: this.username,
      password: "",
      email: "",
      firstName: '',
      lastName: '',
      phone: "",
      address: "",
      enabled: true,
      roles: [{'id':2,'name': "ROLE_CLIENT"}] 
    }
    
    const newVehicle : Vehicle = {
      year: parseInt(this.year),
      make: this.make,
      model: this.model,
      vin: this.vin,
      user: newUser
    }

    console.log(newVehicle);
    
    this.vehicleService.addVehicle(newVehicle).subscribe((vehicle: Vehicle) => (console.log(vehicle.vin)));
    alert('Vehicle saved successfully.');
    
  }

}
