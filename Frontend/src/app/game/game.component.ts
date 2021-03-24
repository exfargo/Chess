import {Component, OnInit} from '@angular/core';
import {Piece} from '../../data/piece';
import {Teams} from '../../data/teams';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  // wtf co je za pojmenovani checkboard
  // i blame karel
  checkboard: Piece[][];
  constructor() {
  }
  ngOnInit(): void {
    this.checkboard = [];
    for (let i = 0; i < 8; i++) {
      this.checkboard.push([]);
      for (let j = 0; j < 8; j++) {
        this.checkboard[i][j] = new Piece('empty', '', Teams.Empty);
      }
    }
    this.checkboard[0][0] = new Piece('rook', 'fas fa-chess-rook', Teams.White);
    this.checkboard[1][0] = new Piece('knight', 'fas fa-chess-knight', Teams.Black);
    this.checkboard[2][0] = new Piece('king', 'fas fa-chess-king', Teams.White);

  }
}

/*
  rook <i class="fas fa-chess-rook"></i>
  queen <i class="fas fa-chess-queen"></i>
  pawn <i class="fas fa-chess-pawn"></i>
  knight <i class="fas fa-chess-knight"></i>
  king <i class="fas fa-chess-king"></i>
 */


