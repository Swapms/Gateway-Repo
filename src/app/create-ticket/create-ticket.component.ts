import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { TicketService } from '../service/ticket.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private router: Router, private apiService: TicketService) { }

  addForm!: FormGroup;
  user ={
    email: window.localStorage.getItem('username'),
    password : ""
  };

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      emailId: ['', Validators.required],
      phoneNo: ['', Validators.required],
      address: ['', Validators.required],
      pin: ['', Validators.required],
      source: ['', Validators.required],
      destination: ['', Validators.required],
      date: ['', Validators.required],
      username : window.localStorage.getItem('username')
    //  user : this.user
    });

  }

  onSubmit() {
    console.log(this.addForm.value)
  
    console.log(this.addForm.value)
    this.apiService.createTicket(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['list-ticket']);
      });
  }

}
