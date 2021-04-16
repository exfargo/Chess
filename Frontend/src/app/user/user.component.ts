import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../services/user.service';
import {Game} from '../../data/game';
import {GameService} from '../services/game.service';
import {NotificationService} from '../services/notification.service';

@Component({
             selector: 'app-user',
             templateUrl: './user.component.html',
             styleUrls: ['./user.component.scss']
           })
export class UserComponent implements OnInit {

  username = '';
  points = 0;
  id = -1;


  games: Game[];

  constructor(private readonly userService: UserService,
              private readonly route: ActivatedRoute,
              private readonly gameService: GameService,
              private readonly notificationService: NotificationService
  ) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      u => this.userService.getPlayer(u.id).subscribe(
        us => {
          this.username = us.username;
          this.points = us.points;
          this.id = us.id;
        },
        er => this.notificationService.pushNotification(er.error, false)),
      e => this.notificationService.pushNotification(e.error, false)
    );

    this.gameService.getByUser(this.id).subscribe(
      g => this.games = g,
      e => this.notificationService.pushNotification(e.error, false)
    );
  }

  challenge(): void {
    this.userService.challenge(this.id).subscribe(
      u => this.notificationService.pushNotification(u.message, true),
      e => this.notificationService.pushNotification(e.error, false)
    );
  }
}
