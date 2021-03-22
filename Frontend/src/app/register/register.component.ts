import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  nickname = '';
  password = '';

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {

  }

  addUser(): void {
    this.userService.createPlayer(this.nickname, this.password).subscribe((data) => {
      console.log(data);
    });

  }

}
