import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  constructor(private httpClient: HttpClient) {
  }

  getPlayer(id: number): Observable<any> {


    return this.httpClient.get('', {
      params: {
        id: id.toString(),

      }
    });
  }
}
