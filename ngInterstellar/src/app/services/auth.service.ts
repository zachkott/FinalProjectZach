import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { tap, catchError, throwError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Set port number to server's port
  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl;

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<User> {
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header specifying Basic HTTP authentication
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };

    // Create GET request to authenticate credentials
    return this.http.get<User>(this.url + 'authenticate', httpOptions).pipe(
      tap((newUser) => {
        // While credentials are stored in browser localStorage, we consider
        // ourselves logged in.
        localStorage.setItem('credentials', credentials);
        return newUser;
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.login(): error logging in user.')
        );
      })
    );
  }

  logout(): void {
    localStorage.removeItem('credentials');
  }

  getLoggedInUser(): Observable<User> {
    if (!this.checkLogin()) {
      return throwError(() => {
        new Error('Not logged in.');
      });
    }
    let httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.getCredentials(),
        'X-Requested-with': 'XMLHttpRequest',
      },
    };
    return this.http.get<User>(this.baseUrl + 'authenticate', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'AuthService.getUserById(): error retrieving user: ' + err
            )
        );
      })
    );
  }

  checkLogin(): boolean {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username: string, password: string): string {
    return Buffer.from(`${username}:${password}`).toString('base64');
  }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }
}
