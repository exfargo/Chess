import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { ResponseMessage } from 'src/data/responseMessage';
import {AppRoutingModule} from '../app-routing.module';
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
    })
  }
}
