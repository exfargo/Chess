import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../data/user';
import {ResponseMessage} from '../../data/responseMessage';
import {Challenge} from '../../data/challenge';
import {Game} from '../../data/game';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {
  }


  getPlayer(id?: number): Observable<User> {
    if (id != null || id !== undefined) {
      return this.httpClient.get('/chess/user/' + id, {
        withCredentials: true
      }) as Observable<User>;
    }

    return this.httpClient.get('/chess/user/authentication', {
      withCredentials: true
    }) as Observable<User>;
  }

  createPlayer(username: string, password: string): Observable<ResponseMessage> {
    return this.httpClient.post('/chess/user/authentication/new', {
      username,
      password
    }, {
      withCredentials: true
    }) as Observable<ResponseMessage>;
  }

  logPlayer(username: string, password: string): Observable<ResponseMessage> {
    return this.httpClient.post('/chess/user/authentication', {
      username,
      password
    }, {
      withCredentials: true
    })  as Observable<ResponseMessage>;
  }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get('/chess/users/all') as Observable<User[]>;
  }

  getLeaderboard(type: string): Observable<User[]> {
    return this.httpClient.get('/chess/lidlboard/' + type) as Observable<User[]>;
  }

  challenge(id: number): Observable<ResponseMessage> {
    return this.httpClient.post('/chess/user/' + id + '/challenge', {}) as Observable<ResponseMessage>;
  }

  getByName(name: string): Observable<User[]> {
    return this.httpClient.get('/chess/users/search?filter=' + name) as Observable<User[]>;
  }

  acceptChallenge(id: number): Observable<ResponseMessage> {
    return this.httpClient.put('/chess/user/challenge/' + id, {}) as Observable<ResponseMessage>;
  }

  getChallenges(): Observable<Challenge[]> {
    return this.httpClient.get('/chess/user/challenge') as Observable<Challenge[]>;
  }

  getGames(): Observable<Game[]> {
    return this.httpClient.get('/chess/game/all') as Observable<Game[]>;
  }
}
