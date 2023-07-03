import { Component } from '@angular/core';
import { User } from 'src/app/entities/User';
import { AccountService } from 'src/app/services/account.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username!: string;
  password!: string;
  confirmPassword!: string;
  email!: string;
  fname!: string;
  lname!: string;
  phone!: string;
  address!: string;
  userCreated!: User;
  
  constructor(private accountService: AccountService, private authService: AuthService){}

  ngOnInit(): void{
  }

  onSubmit(){
    this.authService.deauthenticateClient();
    this.authService.deauthenticateAdjuster();
    if(this.password !== this.confirmPassword){
      alert("Passwords do not match. Please try again.")
      return;
    }
    if(!this.username){
      alert('Please add a username!');
      return;
    } 
    if (!this.password){
      alert('Please add a password!');
      return;
    }
    if (!this.confirmPassword){
      alert('Please add a confirmation password!');
      return;
    }
    if (!this.email){
      alert('Please add an email!');
      return;
    }
    if (!this.fname){
      alert('Please add a first name!');
      return;
    }
    if (!this.lname){
      alert('Please add a last name!');
      return;
    }
    if (!this.phone){
      alert('Please add a phone number!');
      return;
    }
    if (!this.address){
      alert('Please add an address!');
      return;
    }
    const newUser : User = {
      username: this.username,
      password: this.password,
      email: this.email,
      fname: this.fname,
      lname: this.lname,
      phone: this.phone,
      address: this.address,
      enabled: true,
      roles: [{'id':2,'name': "ROLE_CLIENT"}] 
    }
    this.accountService.addUser(newUser).subscribe((user: User) => (this.userCreated = user));
    alert('Account succesfully created. Please use the login tab for logging into the application. If it does not work please try registering a different username.');
    //alert(this.userCreated.username);
    /*
    this.username = '';
    this.password = '';
    this.confirmPassword = '';
    this.email = '';
    this.fname = '';
    this.lname = '';
    this.phone = '';
    this.address = '';
    */
  }

}
