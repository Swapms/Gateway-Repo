import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { TicketService } from '../service/ticket.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor( private apiService: TicketService,
    private router: Router) { }

  ngOnInit(): void {
   // this.authenticationService.logOut();
   this.apiService.logOut();
    this.router.navigate(['login']);
  }

}
