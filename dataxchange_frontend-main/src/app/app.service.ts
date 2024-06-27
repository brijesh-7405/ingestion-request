import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { ApplicationReference, DecisionRequestDTO, IngestionRequest, IngestionRequestDetails } from './models/models';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private url = 'http://localhost:3000/api/v1/';

  private httpWithoutInterceptor: HttpClient; //Bypassed http interceptor - delete

  constructor(private http: HttpClient, private httpBackend: HttpBackend) {
    this.httpWithoutInterceptor = new HttpClient(this.httpBackend);
  }

  saveNotesByRequestIdId(ingestionRequestId: any,payload: any): Observable<any> {
    return this.httpWithoutInterceptor.post<any>(`${this.url}ingestion_requests/${ingestionRequestId}/notes`, payload);
  }

  updateNotesById(notesId: any,ingestionRequestId: any,payload: any): Observable<any> {
    return this.httpWithoutInterceptor.put<any>(`${this.url}ingestion_requests/${ingestionRequestId}/notes/${notesId}`, payload);
  }

  deleteNotesById(id: any,ingestionRequestId: any): Observable<any> {
    return this.httpWithoutInterceptor.delete<any>(`${this.url}ingestion_requests/${ingestionRequestId}/notes/${id}`);
  }

  saveRequest(payload: IngestionRequest,submit: boolean): Observable<any> {
    const url = submit ? `${this.url}ingestion_requests?submit=true` : `${this.url}ingestion_requests`;
    return this.httpWithoutInterceptor.post<any>(url, payload);
  }

  updateRequest(ingestionRequestId: number, payload: IngestionRequest,submit: boolean): Observable<any> {
    const url = submit ? `${this.url}ingestion_requests/${ingestionRequestId}?submit=true` : `${this.url}ingestion_requests/${ingestionRequestId}`;
    return this.httpWithoutInterceptor.put<any>(url, payload);
  }

  getRequestById(id: any): Observable<IngestionRequestDetails> {
    return this.httpWithoutInterceptor.get<any>(`${this.url}ingestion_requests/${id}`)
  }

  fetchAllMyRequests(my_approvals: boolean,my_submissions: boolean,page: number,per_page: number,status: string, orderBy: string, orderDirection: string): Observable<any> {
    const queryParams = {
      my_approvals: my_approvals.toString(),
      my_submissions: my_submissions.toString(),
      status,
      page: page.toString(),
      per_page: per_page.toString(),
      order_by: orderBy,
      order_direction: orderDirection
    };
    const queryString = new URLSearchParams(queryParams).toString();
    return this.httpWithoutInterceptor.get<any>(`${this.url}ingestion_requests?${queryString}`);
  }

  fetchReferenceData(): Observable<ApplicationReference[]> {
    return this.httpWithoutInterceptor.get<ApplicationReference[]>(`${this.url}application_references`);
  }

  approveRequest(ingestionRequestId : number,payload : DecisionRequestDTO): Observable<IngestionRequestDetails> {
    return this.httpWithoutInterceptor.put<any>(`${this.url}ingestion_requests/${ingestionRequestId}/approve`,payload);
  }

  rejectRequest(ingestionRequestId : number,payload : DecisionRequestDTO): Observable<IngestionRequestDetails> {
    return this.httpWithoutInterceptor.put<any>(`${this.url}ingestion_requests/${ingestionRequestId}/reject`,payload);
  }
}