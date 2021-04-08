import {Component, OnInit} from '@angular/core';
import {NotificationService} from '../services/notification.service';
import {UserService} from '../services/user.service';
import {UserEmitterService} from '../services/user-emitter.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  nickname = '';
  password = '';

  // tslint:disable-next-line:max-line-length
  constructor(private userService: UserService, private notificationService: NotificationService,
              private readonly userEmmiter: UserEmitterService) {

  }

  ngOnInit(): void {

  }

  logUser(): void {
    this.userService.logPlayer(this.nickname, this.password).subscribe(
      s => {
        this.notificationService.pushNotification(s, true);
        this.userService.getPlayer().subscribe(u => this.userEmmiter.pushUser(u),
          e => this.notificationService.pushNotification(e, false));
      },
      e => this.notificationService.pushNotification(e, false)
    );
  }
}
