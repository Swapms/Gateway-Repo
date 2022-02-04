import { Component, OnInit } from '@angular/core';
import { Ticket } from '../model/ticket.model';
import {Router} from "@angular/router";
import { TicketService } from '../service/ticket.service';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {

  tickets!: Ticket[];

  constructor(private router: Router, private apiService: TicketService) { }

  ngOnInit(): void {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.apiService.getTicketList(window.localStorage.getItem('username'))
      .subscribe( data => {
        this.tickets = data.result;
      });
  }

  deleteTicket(ticket: Ticket): void {
    this.apiService.deleteTicket(ticket.id)
      .subscribe( data => {
        this.tickets = this.tickets.filter(u => u !== ticket);
      })
  };

  editTicket(ticket: Ticket): void {
    window.localStorage.removeItem("editTicketId");
    window.localStorage.setItem("editTicketId", ticket.id.toString());
    this.router.navigate(['edit-ticket']);
  };

  addTicket(): void {
    this.router.navigate(['add-ticket']);
  };

}
