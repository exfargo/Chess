import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../User';


@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  constructor(private httpClient: HttpClient) {
  }


  getPlayer(id?: number): Observable<User> {
    if (typeof id !== typeof null) {
      return this.httpClient.get('/chess/user/' + id, {
        withCredentials: true
      }) as Observable<User>;
    }
    return this.httpClient.get('/chess/user/authentication', {
      withCredentials: true
    }) as Observable<User>;
  }

  createPlayer(username: string, password: string): Observable<any> {
    return this.httpClient.post('/chess/user/authentication/new', {
      username,
      password
    }, {
      withCredentials: true
    });
  }

  logPlayer(username: string, password: string): Observable<any> {
    return this.httpClient.post('/chess/user/authentication', {
      username,
      password
    }, {
      withCredentials: true
    });
  }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get('/chess/users/all') as Observable<User[]>;
  }

  getLeaderboard(type: string): Observable<User[]> {
    return this.httpClient.get('/chess/lidlboard/' + type) as Observable<User[]>;
  }
}
