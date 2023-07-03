import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authenticatedClient: boolean = false;
  private authenticatedAdjuster: boolean = false;

  constructor() { }

  isAuthenticatedClient(): boolean {
    return this.authenticatedClient;
  }
  isAuthenticatedAdjuster(): boolean {
    return this.authenticatedAdjuster;
  }

  authenticateAdjuster(): void {
    this.authenticatedAdjuster = true;
  }
  authenticateClient(): void {
    this.authenticatedClient = true;
  }
  deauthenticateAdjuster(): void {
    this.authenticatedAdjuster = false;
  }
  deauthenticateClient(): void {
    this.authenticatedClient = false;
  }
}
