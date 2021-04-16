import {Component, OnInit} from '@angular/core';
import {NotificationService} from '../services/notification.service';
import {Notification} from '../../data/notification';

@Component({
             selector: 'app-notification',
             templateUrl: './notification.component.html',
             styleUrls: ['./notification.component.scss']
           })
export class NotificationComponent implements OnInit {
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
    this.notificationService.close();
  }
}
