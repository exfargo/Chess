import { Component, OnInit } from '@angular/core';
import request from 'axios';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username = '';
  password = '';

  constructor() { }

  ngOnInit(): void {
  }

  login(): void {
    request.post('http://localhost:9090/', {username: this.username, password: this.password}).then(r => {
      if (r.data !== false) { alert(`password and username combination doesn't match`); }
    });
  }
}
