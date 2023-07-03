import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-user-navbar',
  templateUrl: './user-navbar.component.html',
  styleUrls: ['./user-navbar.component.css']
})
export class UserNavbarComponent {
  constructor(private authenticationService: AuthService,
    private router: Router){}

  logout(){
    if(confirm("Are you sure you want to log out? ")){
      this.authenticationService.deauthenticateClient();
      this.authenticationService.deauthenticateAdjuster();
      localStorage.clear();
      this.router.navigate(['']);
    }
  }
  currentUser(): string{
    return localStorage.getItem('currentUser') as string;
  }
  showAdjusterTab(): boolean {
    return this.authenticationService.isAuthenticatedAdjuster();
  }

  clientRegistered(): boolean{
    let clientId = parseInt(localStorage.getItem('clientId') || '0', 10);
    if(clientId == 0){
    return false;
  } else {
    return true;
  }

  }

}
