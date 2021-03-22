import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  username = '';
  points = 0;
  id = -1;

  constructor(private userService: UserService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      u => this.userService.getPlayer(u.id).subscribe(
        us => {
          this.username = us.username;
          this.points = us.points;
          this.id = us.id;
        },
        er => console.log(er)),
      e => console.log(e)
    );
  }

  challenge(): void {
    // TODO karle challengni ho
  }
}
