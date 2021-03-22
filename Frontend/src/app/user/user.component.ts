import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../services/user.service';
import {Game} from '../../data/game';
import {GameService} from '../services/game.service';

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
              private readonly gameService: GameService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      u => this.userService.getPlayer(u.id).subscribe(
        us => {
          this.username = us.username;
          this.points = us.points;
          this.id = us.id;
        },
        er => console.log(er)),
      e => console.log(e)
    );

    this.gameService.getByUser(this.id).subscribe(
      g => this.games = g,
      e => console.log(e)
    );
  }

  challenge(): void {
    this.userService.challenge(this.id).subscribe(
      u => console.log(u.message),
      e => console.log(e)
    );
  }
}
