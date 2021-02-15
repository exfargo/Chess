import {Component, OnInit} from '@angular/core';
import axios from "axios";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  userRegister: string[] = [];

  constructor() {
  }

  ngOnInit(): void {
    axios.get(``)
      .then(file => {
        this.userRegister.push(file.data);
      })

  }

}
