import {Component, OnInit} from '@angular/core';
import {User} from '../../User';
import {Router} from '@angular/router';
import {UserService} from '../user.service';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: User[];

  constructor(private readonly userService: UserService, private readonly router: Router) {
  }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(
      u => this.users = u,
      e => console.log(e)
    );
  }

  goToUser(param: number): void {
    this.router.navigateByUrl('/user/' + param);
  }
}
