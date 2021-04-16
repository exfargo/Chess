import {Component, OnInit} from '@angular/core';
import {NotificationService} from './services/notification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'Chess';

  constructor(private readonly notificationService: NotificationService) {
  }

  ngOnInit(): void {

  }

  closeNotification(): void {
    this.notificationService.close();
  }


}
