import {Component, OnInit} from '@angular/core';
import {NotificationService} from './services/notification.service';
import {Notification} from '../data/notification';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'Chess';

  notification: Notification;

  constructor(private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.notificationService.activeNotification.subscribe(data => {
      this.notification = data;
    });
  }

  getStatus(): boolean {
    return this.notification.status;
  }

  cancel(): void {
    this.notification = null;
  }
}
