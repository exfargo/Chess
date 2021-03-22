import {Component, OnInit} from '@angular/core';
import {User} from '../../User';
import {Router} from '@angular/router';
import {ApiServiceService} from '../apiService.service';


@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.scss']
})
export class LeaderboardComponent implements OnInit {


  constructor(private readonly router: Router, private readonly apiService: ApiServiceService) {}


  players: any = [];
  users: User[];

  ngOnInit(): void {
    this.apiService.getLeaderboard('top-50').subscribe(
      u => this.users = u,
      e => console.log(e)
    );
  }

  goToUser(param: number): void {
    this.router.navigateByUrl('/user/' + param);
  }
}
