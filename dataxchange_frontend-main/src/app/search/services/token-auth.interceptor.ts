import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpErrorResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { mergeMap, switchMap, catchError, } from 'rxjs/operators';
// import { environment } from 'vanilla-search/src/environments/environment';
import { AuthService } from './auth.service';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  kongAPI="";
  kongUrlBase="";
  kongToken="";
  apiKey="";
  
  constructor(private authService: AuthService) {
   }
  /**
   * Intercept the requests to validate the kong access token
   * Validate token only for services exposed through the kong service
   *     (The Kong service URL would look like https://###.api.cutom.com/)
   * When token is not available in the cookie, regenerate the token 
   * When the token service response is positive, add the bearer token to the request headers 
   * When the token service response is negative, return error response
   */
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<Object>> {
    return of(null).pipe(mergeMap(() => {
      //kong token generation proceed further with the processing
      if (request?.url.includes(this.kongAPI) && request.method === 'POST') {
        return next.handle(request);
      }
      //for all the kong apis validate the token in cookie and process the request
      else {
        return this.getAccessToken(request, next);
        //const token = this.tokenService.getToken();
        const token = sessionStorage.getItem('domain-token');
        const isTokenExpired: boolean = this.checkTokenExpired(token)
        //when token not present in cookie, re-generate the token (or) refresh the token
        // if (!token || (token && isTokenExpired))
        //   return this.getAccessToken(request, next);
        // else {
        //   return next.handle(this.addTokenHeader(request, token));
        // }
      }
      return next.handle(request);
    }));
  }


  private getAccessToken(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<Object>> {
    return this.authService.refreshToken().pipe(
      switchMap((token: any) => {
        //when the kong token generation is successfull, store token in the cookie
       // this.tokenService.saveToken(token.access_token);
        //add the bearer token to the request headers
        sessionStorage.setItem('domain-token',token.access_token)
        return next.handle(this.addTokenHeader(request, token.access_token));
      }),
      catchError((err) => {
        if (err instanceof HttpErrorResponse && (err.status === 401 || err.status === 500)) {
          //when token service returns error response, delete the cookies
          // this.tokenService.signOut();
          sessionStorage.removeItem('domain-token');
          return throwError(err);
        }
        return throwError(err);
      }));
  }

  private addTokenHeader(request: HttpRequest<any>, token: string) {
    const kongToken = this.kongToken;
    const apiKey = this.apiKey;
   // if (request?.url.includes(this.kongUrlBase)) { /* for Spring Boot back-end */
      return request.clone({ setHeaders: { Authorization: 'Bearer ' + token, 'kong-token': kongToken, apiKey } });
    //}
   // return request;
  }
  
  private checkTokenExpired (tokenKey:any) {
    const jwtPayload = JSON.parse(window.atob(tokenKey.split('.')[1]))
    return Date.now() >= jwtPayload.exp * 1000;
  }


}