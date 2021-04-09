import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs/internal/BehaviorSubject';
import {User} from '../../data/user';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserEmitterService {


  private userSource = new BehaviorSubject<User>(null);
  userActive = this.userSource.asObservable();

  constructor() {
  }

  pushUser(user: User): void {
    this.userSource.next(user);

  }
}
