import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {User} from '../../data/user';
import {UserEmitterService} from '../services/user-emitter.service';
import {HttpClient} from '@angular/common/http';
import {NotificationService} from '../services/notification.service';

@Component({
             selector: 'app-header',
             templateUrl: './header.component.html',
             styleUrls: ['./header.component.scss']
           })
export class HeaderComponent implements OnInit {

  user: User;
  logged = false;

  constructor(private readonly router: Router,
              private readonly userService: UserService,
              private readonly userSource: UserEmitterService,
              private readonly httpClient: HttpClient,
              private readonly notificationService: NotificationService) {
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
    return !this.logged;
  }

  logOut(): void {
    this.userSource.pushUser(null);
    this.userService.logoutPlayer().subscribe(
      u => this.notificationService.pushNotification(u.message, true),
      e => this.notificationService.pushNotification(e.error, false)
    );
  }

}
