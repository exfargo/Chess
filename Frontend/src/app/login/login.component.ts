import {Component, OnInit} from '@angular/core';
import {NotificationService} from '../services/notification.service';
import {UserService} from '../services/user.service';
import { UserEmitterService } from '../services/user-emitter.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  nickname = '';
  password = '';

  constructor(private userService: UserService, private notificationService: NotificationService, private readonly userEmmiter: UserEmitterService) {

  }

  ngOnInit(): void {

  }

  logUser(): void {
    this.userService.logPlayer(this.nickname, this.password).subscribe(
      s => {
        this.notificationService.pushNotification(s, true);
        this.userEmmiter.pushUser(true);
      },
      e => this.notificationService.pushNotification(e, false)
    );
  }
}
