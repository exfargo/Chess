import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {User} from '../../data/user';
import {UserEmitterService} from '../services/user-emitter.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user: User;
  logged = false;

  constructor(private readonly router: Router, private readonly userService: UserService, private readonly userSource: UserEmitterService) {
  }

  ngOnInit(): void {
    this.userSource.userActive.subscribe(user => {
        this.user = user;
        this.logged = user !== null;
      },
      e => this.logged = false);
  }

  goToUser(): void {
    this.router.navigateByUrl('/user/' + this.user.id);
  }

  showThis(): boolean {
    console.log(this.logged);
    return !this.logged;
  }
}
