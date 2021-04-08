import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class UserEmitterService {


  private userSource = new BehaviorSubject<boolean>(false);
  userActive = this.userSource.asObservable();

  constructor() {
  }

  pushUser(status: boolean): void {
    this.userSource.next(status);
  }
}
