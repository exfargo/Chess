import {Component, OnInit} from '@angular/core';
import {User} from '../../data/user';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {NotificationService} from '../services/notification.service';


@Component({
             selector: 'app-leaderboard',
             templateUrl: './leaderboard.component.html',
             styleUrls: ['./leaderboard.component.scss']
           })
export class LeaderboardComponent implements OnInit {


  constructor(private readonly router: Router,
              private readonly userService: UserService,
              private readonly notificationService: NotificationService) {
  }


  players: any = [];
  users: User[];

  ngOnInit(): void {
    this.userService.getLeaderboard('top-50').subscribe(
      u => this.users = u,
      e => this.notificationService.pushNotification(e.error, false)
    );
  }

  goToUser(param: number): void {
    this.router.navigateByUrl('/user/' + param);
  }
}
