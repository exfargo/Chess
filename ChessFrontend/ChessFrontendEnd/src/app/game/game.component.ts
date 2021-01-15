import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  checkboard = [["br","bk","bb","bq","bk","bb","bk","br"],
                ["bp","bp","bp","bp","bp","bp","bp","bp"],
                ["","","","","","","",""],
                ["","","","","","","",""],
                ["","","","","","","",""],
                ["","","","","","","",""],
                ["wp","wp","wp","wp","wp","wp","wp","wp"],
                ["wr","wk","wb","wq","wk","wb","wk","wr"]];
  constructor() { }

  ngOnInit(): void {
  }

}
