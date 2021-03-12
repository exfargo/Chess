import {Component, OnInit} from '@angular/core';
import {ApiServiceService} from '../apiService.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  nickname = '';
  password = '';

  constructor(private apiService: ApiServiceService) {

  }

  ngOnInit(): void {

  }

  logUser(): void {
    this.apiService.logPlayer(this.nickname, this.password).subscribe((data) => {

      this.apiService.getPlayer().subscribe((data) => {
        console.log(data)
      });
    });

  }
}
