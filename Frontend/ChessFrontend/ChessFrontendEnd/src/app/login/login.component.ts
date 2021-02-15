import {Component, OnInit} from '@angular/core';
import {ApiServiceService} from "../apiService.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private apiService: ApiServiceService) {

  }

  ngOnInit(): void {
    this.apiService.getPlayer(0).subscribe((data) => {
      console.log(data);
    });
  }
}
