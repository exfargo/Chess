import { Component, OnInit } from '@angular/core';
import request from 'axios';
import {Router} from '@angular/router';

class User {
  username = '';
}

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  username = 'loading';

  constructor(private route: Router) {}

  ngOnInit(): void {
    request.get('https:\\localhost:9090\\ano').then(data => {
      const nm: User = data.data;
      console.log(nm);
      if (nm.username != null) {
        this.username = nm.username;
      } else {
        // @ts-ignore
        document.getElementById('userFlex').innerHTML += '<a routerLink="user/login">Login</a>';
        // @ts-ignore
        document.getElementById('userFlex').innerHTML += '<a routerLink="user/register">Register</a>';
      }
    });
  }
}
