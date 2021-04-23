import {Component, OnInit} from '@angular/core';
import {NotificationService} from '../services/notification.service';
import {UserService} from '../services/user.service';
import {UserEmitterService} from '../services/user-emitter.service';
import {Router} from '@angular/router';

@Component({
             selector: 'app-login',
             templateUrl: './login.component.html',
             styleUrls: ['./login.component.scss']
           })
export class LoginComponent implements OnInit {
  nickname = '';
  password = '';

  constructor(private userService: UserService, private notificationService: NotificationService,
              private readonly userEmitter: UserEmitterService, private readonly router: Router) {

  }

  ngOnInit(): void {

  }

  logUser(): void {
    this.userService.logPlayer(this.nickname, this.password).subscribe(
      s => {
        this.notificationService.pushNotification(s.message, true);
        this.userService.getPlayer().subscribe(
          u => {
            this.userEmitter.pushUser(u);
            this.router.navigateByUrl('/dashboard');
          },
          e => {
            this.notificationService.pushNotification(e.error, false);
          });
      },
      e => {
        this.notificationService.pushNotification(e.error, false);
      }
    );
  }
}
