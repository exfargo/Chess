import {Component, ElementRef, OnInit} from '@angular/core';
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
  selectedTile: Element;
  board: ElementRef;
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
  // někdy se selectne jenom ta figurka vim že to šlo nějak zpravit ale nevim jak
  clickedTile(clickEvent: MouseEvent, clickedPiece: Piece): void {
    // unselect old tile
    if (this.selectedTile !== undefined) {
      this.selectedTile.classList.remove('selected-tile');
    }
    // select new tile
    this.selectedTile = (clickEvent.target as Element);
    this.selectedTile.classList.add('selected-tile');
  }
}

/*
  rook <i class="fas fa-chess-rook"></i>
  queen <i class="fas fa-chess-queen"></i>
  pawn <i class="fas fa-chess-pawn"></i>
  knight <i class="fas fa-chess-knight"></i>
  king <i class="fas fa-chess-king"></i>
 */


