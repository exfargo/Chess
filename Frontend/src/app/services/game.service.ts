import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Game} from '../../data/game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private readonly httpClient: HttpClient) {}

  getByUser(userId: number): Observable<Game[]> {
    return this.httpClient.get('/chess/game/user/' + userId) as Observable<Game[]>;
  }

}
