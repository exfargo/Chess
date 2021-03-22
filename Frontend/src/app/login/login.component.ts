import {Component, OnInit} from '@angular/core';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  nickname = '';
  password = '';

  constructor(private userService: UserService) {

  }

  ngOnInit(): void {

  }

  logUser(): void {
    this.userService.logPlayer(this.nickname, this.password).subscribe((data) => {
      console.log('jes');
    });

  }
}
