import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  constructor(private httpClient: HttpClient) {
  }

  getPlayer(): Observable<any> {
    return this.httpClient.get('http://25.67.249.190:8080/Chess/chess/user/authentication', {
      withCredentials: true
    });
  }

  createPlayer(username: string, password: string): Observable<any> {
    return this.httpClient.post('http://25.67.249.190:8080/Chess/chess/user/authentication/new', {
      username,
      password
    }, {
      withCredentials: true
    });
  }

  logPlayer(username: string, password: string): Observable<any> {
    return this.httpClient.post('http://25.67.249.190:8080/Chess/chess/user/authentication', {
      username,
      password
    }, {
      withCredentials: true
    });
  }
}
