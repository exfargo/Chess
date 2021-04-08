import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {User} from '../../data/user';
import {UserEmitterService} from '../services/user-emitter.service';
import {printLine} from 'tslint/lib/verify/lines';
import {Observable, Observer} from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user: User;

  constructor(private readonly router: Router, private readonly userService: UserService, private readonly userSource: UserEmitterService) {
  }

  ngOnInit(): void {
    this.userSource.userActive.subscribe(user => this.user = user);
  }

  goToUser(): void {
    this.router.navigateByUrl('/user/' + this.user.id);
  }

  showThis(): boolean {
    // TODO KARLE OPRAV TO
    return true;
  }
}
