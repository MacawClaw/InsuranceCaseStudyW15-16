import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-create-claim',
  templateUrl: './create-claim.component.html',
  styleUrls: ['./create-claim.component.css']
})
export class CreateClaimComponent {
  username: string | null;

  constructor(private http: HttpClient, private clientService: ClientService){
    this.username = clientService.username;
  }
}
