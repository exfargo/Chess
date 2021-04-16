import {Component, OnInit} from '@angular/core';
import {UserService} from '../services/user.service';
import {NotificationService} from '../services/notification.service';

@Component({
             selector: 'app-register',
             templateUrl: './register.component.html',
             styleUrls: ['./register.component.scss']
           })
export class RegisterComponent implements OnInit {
  nickname = '';
  password = '';

  constructor(private readonly userService: UserService,
              private readonly notificationService: NotificationService
  ) {}

  ngOnInit(): void {

  }

  createUser(): void {
    this.userService.createPlayer(this.nickname, this.password).subscribe(
      u => this.notificationService.pushNotification(u.message, true),
      e => {
        this.notificationService.pushNotification(e.error.message, false);
      }
    );
  }

}
