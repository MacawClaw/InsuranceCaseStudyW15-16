import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from 'src/app/entities/Role';
import { User } from 'src/app/entities/User';
import { AccountService } from 'src/app/services/account.service';
import { AuthService } from 'src/app/services/auth.service';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username!: string;
  password!: string;
  user!: User;

  constructor(private accountService: AccountService,
              private authenticationService: AuthService,
              private clientService: ClientService, 
              //private projectService: ProjectService,
              private authService: AuthService,
              private router: Router){

  }

  ngOnInit(): void{
    localStorage.setItem('currentUser', '');
  }

  async onSubmit(){
    this.authService.deauthenticateClient();
    this.authService.deauthenticateAdjuster();
    let rawuser = {
      username: this.username,
      password: this.password,
      email: '',
      firstName: '',
      lastName: '',
      phone: '',
      address: '',
      enabled: true,
      roles: []
    };
    //add localstorage.clear when user press logout
    localStorage.setItem('currentUser', rawuser.username);
    this.accountService.loginCheck(rawuser).subscribe((user) => {
      this.user = user;
      localStorage.setItem('currentName', user.firstName);
      let isAdjuster:boolean = false;
      if(this.user.enabled == false) {
        alert("Account not enabled or details are wrong, please try again.")
        this.username = '';
        this.password = '';
      } else {
        this.username = '';
        this.password = '';
        localStorage.setItem('authKey', 'Basic ' + btoa(`${rawuser.username}:${rawuser.password}`));
        this.user.roles.forEach((role: Role) => {
          if(role.name === 'ROLE_ADJUSTER'){
            isAdjuster = true;
            console.log(isAdjuster);
          }
        })

        //Simplified login process to test backend


        
        this.clientService.updateUsername();
        this.authenticationService.authenticateClient();
        this.router.navigateByUrl('/userhomepage');
        /*
        this.clientService.getClientByUsername().subscribe((client) => {
          if(client == null){
            localStorage.setItem('clientId', '0');
            this.projectService.updateClientIdLocal();
            this.router.navigate(['/dashboard']);
          } else {
            localStorage.setItem('clientId', '' + client.clientId);
            this.projectService.updateClientIdLocal();
            this.router.navigate(['/dashboard']);
          }
        }
        */

        
        if(isAdjuster){
          this.authenticationService.authenticateAdjuster();       
        }
        
      }
    })
  }

}
