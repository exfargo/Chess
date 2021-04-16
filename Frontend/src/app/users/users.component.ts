import {Component, OnInit} from '@angular/core';
import {User} from '../../data/user';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {NotificationService} from '../services/notification.service';


@Component({
             selector: 'app-users',
             templateUrl: './users.component.html',
             styleUrls: ['./users.component.scss']
           })
export class UsersComponent implements OnInit {
  users: User[];
  search: string;

  constructor(private readonly userService: UserService,
              private readonly router: Router,
              private readonly notificationService: NotificationService
  ) {
  }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(
      u => this.users = u,
      e => this.notificationService.pushNotification(e.error, false)
    );
  }

  updateBySearch(): void {
    this.userService.getByName(this.search).subscribe(
      u => this.users = u,
      e => this.notificationService.pushNotification(e.error, false)
    );
  }

  goToUser(param: number): void {
    this.router.navigateByUrl('/user/' + param);
  }
}
