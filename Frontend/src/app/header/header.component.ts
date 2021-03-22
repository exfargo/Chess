import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {User} from '../../data/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user: User;

  constructor(private readonly router: Router, private readonly userService: UserService) {
  }

  ngOnInit(): void {
  }

  log(): void {
    this.userService.getPlayer().subscribe(
      u => this.user = u,
      e => console.log(e)
    );
  }

  goToUser(): void {
    this.router.navigateByUrl('/user/' + this.user.id);
  }
}
