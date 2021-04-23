import {Component, OnInit} from '@angular/core';
import {Challenge} from '../../data/challenge';
import {UserService} from '../services/user.service';
import {Game} from '../../data/game';
import {NotificationService} from '../services/notification.service';
import {Router} from '@angular/router';

@Component({
             selector: 'app-dashboard',
             templateUrl: './dashboard.component.html',
             styleUrls: ['./dashboard.component.scss']
           })
export class DashboardComponent implements OnInit {
  challenges: Challenge[];
  games: Game[];

  constructor(private readonly userService: UserService,
              private readonly notificationService: NotificationService,
              private readonly router: Router) {
  }

  ngOnInit(): void {
    this.userService.getChallenges().subscribe(
      c => this.challenges = c,
      e => this.notificationService.pushNotification(e.error.message, false)
    );
    this.userService.getGames().subscribe(
      g => {
        this.games = g;
      },
      e => this.notificationService.pushNotification(e.error.message, false)
    );
  }

  accept(id: number): void {
    this.userService.acceptChallenge(id).subscribe(
      s => this.notificationService.pushNotification(s.message, true),
      e => this.notificationService.pushNotification(e.error, false)
    );
    this.ngOnInit();
  }

  enterGame(id: number): void {
    this.router.navigateByUrl('/game/' + id);
  }
}
