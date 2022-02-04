import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../model/api.response";
import {Ticket} from "../model/ticket.model";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor(private http: HttpClient) { }

  //baseUrl: string = 'http://localhost:9052/springboot-crud-rest/users/';
  baseUrl : string = 'http://localhost:8080/service1/api/';

  login(loginPayload : any) : Observable<ApiResponse> {
    console.log("in login " + loginPayload)
    let headers = new HttpHeaders();
    headers.set('content-type','application/json; charset=utf-8');
    return this.http.post<ApiResponse>(this.baseUrl + 'signin', loginPayload,{headers : headers});
    //return this.http.post<ApiResponse>('http://localhost:9052/springboot-crud-rest/' + 'token/generate-token', loginPayload,{headers : headers});
  }

  getTicketList(email:any) : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl+'bookingService/booking/v2/users/'+email);
  }

  getTicketById(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl + 'bookingService/booking/v2/users/ticket/' + id);
  }

  createTicket(ticket: Ticket): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.baseUrl+'bookingService/booking/v2/users/', ticket);
  }

  updateTicket(ticket: Ticket): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.baseUrl +'bookingService/booking/v2/users/' + ticket.id, ticket);
  }

  deleteTicket(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.baseUrl +'bookingService/booking/v2/users/' + id);
  }

  logOut(): Observable<ApiResponse> {
    let headers = new HttpHeaders();   
    let username = window.localStorage.getItem('username') || '{}';
   // user = window.localStorage.getItem('username');  
    headers.set('Authorization',username);
    window.localStorage.removeItem('username');
    return this.http.post<ApiResponse>(this.baseUrl+'signout',{headers : headers});
  }
}
