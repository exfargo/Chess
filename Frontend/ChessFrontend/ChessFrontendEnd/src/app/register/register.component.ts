import {Component, OnInit} from '@angular/core';
import {ApiServiceService} from '../apiService.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  nickname = '';
  password = '';

  constructor(private apiService: ApiServiceService) {
  }

  ngOnInit(): void {

  }

  addUser(): void {
    this.apiService.createPlayer(this.nickname, this.password).subscribe((data) => {
      console.log(data);
    });

  }

}
