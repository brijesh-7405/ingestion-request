import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { encrypt, decrypt } from '../../../utils/crypt';
import moment from 'moment';
const TOKEN_KEY = '__RDAH';
const REFRESHTOKEN_KEY = 'auth-refreshtoken';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class KongTokenService {

  constructor(private cookieService: CookieService) { }
  signOut(): void {
    window.sessionStorage.clear();
    this.cookieService.deleteAll();
  }

  public saveToken(token: string): void {
    this.cookieService.delete(TOKEN_KEY);
    this.cookieService.set(TOKEN_KEY, encrypt(token), new Date(new Date().getTime() + 1000 * 60 * 60),'/', undefined , true, "Lax");
    const user = this.getUser();
    if (user.id) {
      this.saveUser({ ...user, access_token: token });
    }
  }

  public getToken(): string | null {
    var token  = this.getDecodedToken(this.cookieService.get(TOKEN_KEY));
    return decrypt(token.code);
  }

  public saveRefreshToken(token: string): void {
    this.cookieService.delete(REFRESHTOKEN_KEY);
    this.cookieService.set(REFRESHTOKEN_KEY, encrypt(token));
  }

  public getRefreshToken(): string | null {
    return decrypt(this.cookieService.get(REFRESHTOKEN_KEY));
  }

  public saveUser(user: any): void {
    this.cookieService.delete(USER_KEY);
    this.cookieService.set(USER_KEY, encrypt(JSON.stringify(user)));
  }

  public getUser(): any {
    const user = decrypt(this.cookieService.get(USER_KEY));
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }


  public getDecodedToken(token:any): any {
    var decodedToken = JSON.parse(atob(token));
    var payload: any = {};
    payload.exp = decodedToken.passport.user.exp;
    payload.code = decodedToken.passport.user.code;
    payload.aud = decodedToken.passport.user.aud;   
    return payload;
  }

  public isExpired(exp:any): any {
    const expired_time = exp && !isNaN(exp) ? +exp - 60 : 0;
    const current_time = +moment().unix();
    return (current_time > expired_time);
  }

}
