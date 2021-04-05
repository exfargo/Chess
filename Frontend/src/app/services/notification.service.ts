import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {ResponseMessage} from 'src/data/responseMessage';
import {Notification} from '../../data/notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private notificationSource = new BehaviorSubject<Notification>(null);
  activeNotification = this.notificationSource.asObservable();

  constructor() {
  }

  // TODO Karle vsude kde se nejak operuje s apickem, tak pouzij tohle na vypisovani vysledku

  // TODO pres tohle se bude i dobre delat to prihlasovani uzivatelu, protoze tahle service je
  // vzdycky actualni, AKA nemusis ji aktualizovat,  a je stejna pro vsechny komponenty

  /*
  example use:
    constructor(private readonly nejakaJinaServica: NejakaJinaServica, private readonly notificationService: NotificationService){}

    funkce(): any {
      this.nejakaJinaServica.post({username:"Aloij", password:"123").subscribe(data =>
        u => this.notificationService.push(u, true)
        e => this.notificationService.push(e, false)
      });
    }
   */

  pushNotification(message: ResponseMessage, status: boolean): void {
    this.notificationSource.next({message: message.message, status});
  }
}
