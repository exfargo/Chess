import request from 'axios';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  username = '';
  password = '';
  age = false;
  badPassword = false;

  constructor() { }

  ngOnInit(): void {
  }

  register(): void {
    if (this.isValid(this.username, this.password, this.age)) {
      request.post('http://localhost:9090/', {username: this.username, password: this.password, age: this.age}).then(r => alert(`Account creation${r}`));
    }
  }

  isValid(username: string, password: string, age: boolean): boolean {
    let error = true;
    if (!username.match('[a-zA-z0-9]{4,}')) {
      // error css to username input
      error = false;
    }
    if (!password.match('[a-zA-z0-9]{4,}') && password.length < 3) {
      // error to password input
      this.badPassword = true;
      error = false;
    }
    if (!age) {
      // error to age input
      error = false;
    }
    return error;
  }
}
