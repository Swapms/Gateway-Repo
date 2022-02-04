import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  isUserLoggedIn() {
    let user = window.localStorage.getItem('username')
    console.log(user)
   // console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    window.localStorage.removeItem('username')
  }
}
