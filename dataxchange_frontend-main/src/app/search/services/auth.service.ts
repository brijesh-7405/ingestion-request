import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
// import { environment } from 'vanilla-search/src/environments/environment';
import { Observable } from 'rxjs';


const AUTH_API = "https://dev.api.cutom.com/token";
//environment.kongAPI;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  clientId = 'e42adb9bcc7f4492a804bf34c22f81d5';
  clientSecret = 'QmBdmrpWbxPn4DpsTAoKwYpX1KAIZSkFA1e8iOfPFoHAblecAu7jomtzvoSPDKujfvAj';
  constructor(private http: HttpClient) { }

  // login, register

  refreshToken():Observable<any> {
    let body = new HttpParams()
        .set('client_id', this.clientId)
        .set('client_secret', this.clientSecret)
        .set('grant_type', 'client_credentials')
        .set('scope', 'openid');
    return this.http.post(AUTH_API,body, httpOptions);
  }
}
