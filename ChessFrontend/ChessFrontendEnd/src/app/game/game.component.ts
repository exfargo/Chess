import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  checkboard = [["br","bh","bb","bq","bk","bb","bh","br"],
                ["bp","bp","bp","bp","bp","bp","bp","bp"],
                ["","","","","","","",""],
                ["","","","","","","",""],
                ["","","","","","","",""],
                ["","","","","","","",""],
                ["wp","wp","wp","wp","wp","wp","wp","wp"],
                ["wr","wh","wb","wq","wk","wb","wh","wr"]];
  constructor() { }

  ngOnInit(): void {
  }

}
