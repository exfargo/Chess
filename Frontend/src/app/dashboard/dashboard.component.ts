import {Component, OnInit} from '@angular/core';
import {Challenge} from '../../data/challenge';
import {UserService} from '../services/user.service';
import {Game} from '../../data/game';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  challenges: Challenge[];
  games: Game[];

  constructor(private readonly userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getChallenges().subscribe(
      c => this.challenges = c,
      e => console.log(e)
    );
    this.userService.getGames().subscribe(
      g => {
        console.log(g);
        this.games = g;
      },
      e => console.log(e)
    );
  }

  accept(id: number): void {
    this.userService.acceptChallenge(id).subscribe(
      s => this.userService.acceptChallenge(id),
      e => console.log(e)
    );
  }


  enterGame(id: number): void {

  }
}
