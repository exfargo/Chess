import {Component, OnInit} from '@angular/core';
import {User} from '../../data/user';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: User[];
  search: string;

  constructor(private readonly userService: UserService, private readonly router: Router) {
  }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(
      u => this.users = u,
      e => console.log(e)
    );
  }

  updateBySearch(): void {
    this.userService.getByName(this.search).subscribe(
      u => this.users = u,
      e => console.log(e)
    );
  }

  goToUser(param: number): void {
    this.router.navigateByUrl('/user/' + param);
  }
}
