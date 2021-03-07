import { Component, OnInit } from '@angular/core';
import {ApiServiceService} from "../apiService.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  username = "placeholder";
  elo = 0;

  constructor(private apiService:ApiServiceService) { }

  ngOnInit(): void {
    this.apiService.getPlayer().subscribe((data) => {
      console.log(data);
    })
  }

}
