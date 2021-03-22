import {Component, OnInit} from '@angular/core';
import {User} from '../../User';
import {ApiServiceService} from '../apiService.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: User[];

  constructor(private readonly apiService: ApiServiceService, private readonly router: Router) {
  }

  ngOnInit(): void {
    this.apiService.getAllUsers().subscribe(
      u => this.users = u,
      e => console.log(e)
    );
  }

  goToUser(param: number): void {
    this.router.navigateByUrl('/user/' + param);
  }
}
