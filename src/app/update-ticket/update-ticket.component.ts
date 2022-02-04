import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {first} from "rxjs/operators";
import { TicketService } from '../service/ticket.service';
import { Ticket } from '../model/ticket.model';

@Component({
  selector: 'app-update-ticket',
  templateUrl: './update-ticket.component.html',
  styleUrls: ['./update-ticket.component.css']
})
export class UpdateTicketComponent implements OnInit {

  ticket!: Ticket;
  editForm!: FormGroup;
  constructor(private formBuilder: FormBuilder,private router: Router, private apiService: TicketService) { }

  ngOnInit() {
    let ticketId = window.localStorage.getItem("editTicketId");
    let username = window.localStorage.getItem('username');
    if(!ticketId) {
      alert("Invalid action.")
      this.router.navigate(['list-ticket']);
      return;
    }
    this.editForm = this.formBuilder.group({
      id: [''],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      emailId: ['', Validators.required],
      phoneNo: [''],
      address: ['', Validators.required],
      pin: ['', Validators.required],
      source: ['', Validators.required],
      destination: ['', Validators.required],
      date: ['', Validators.required],
    });
   
    this.apiService.getTicketById(+ticketId)
      .subscribe( data => {
        console.log(data)
        this.editForm.setValue(data.result);
      });
  }

  onSubmit() {
    this.apiService.updateTicket(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if(data.status === 200) {
            alert('User updated successfully.');
            this.router.navigate(['list-ticket']);
          }else {
            alert(data.message);
          }
        },
        error => {
          alert(error);
        });
  }


}
