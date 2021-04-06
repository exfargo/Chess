import {Component, ElementRef, OnInit} from '@angular/core';
import {Piece} from '../../data/piece';
import {Teams} from '../../data/teams';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  constructor() {
  }
  // wtf co je za pojmenovani checkboard
  // i blame karel
  checkboard: Piece[][];
  selectedTile: Element;
  board: ElementRef;
  selectedX: number;
  selectedY: number;
  // asi se potom bude nejak brat z apicka??
  playingTeam: Teams = Teams.White;
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
  clickedTile(clickEvent: MouseEvent, clickedPiece: Piece, y: number, x: number): void {
    if (clickedPiece.getTeam() === this.playingTeam) {
      this.selectNewTile(clickEvent, x, y);
    } else if (this.selectedTile !== undefined) {
      this.makeMove(this.selectedX, this.selectedY, x, y);
    }
  }

  selectNewTile(clickEvent: MouseEvent, x: number, y: number): void{
    this.unselectTile();
    // select new tile
    this.selectedTile = (clickEvent.target as Element);
    // fix aby se vybralo celi policko a ne jenom ta figurka
    if (this.selectedTile.tagName !== 'TD') { this.selectedTile = this.selectedTile.parentElement; }
    this.selectedX = x;
    this.selectedY = y;
    this.selectedTile.classList.add('selected-tile');
  }
  unselectTile(): void{
    if (this.selectedTile !== undefined) {
      this.selectedTile.classList.remove('selected-tile');
      this.selectedTile = undefined;
      this.selectedX = undefined;
      this.selectedY = undefined;
    }
  }
  makeMove(sx: number, sy: number, tx: number, ty: number): void{
    console.log('attempted move');
    console.log('source :' + sx + ',' + sy);
    console.log('target :' + tx + ',' + ty);
    this.unselectTile();
  }
}

/*
  rook <i class="fas fa-chess-rook"></i>
  queen <i class="fas fa-chess-queen"></i>
  pawn <i class="fas fa-chess-pawn"></i>
  knight <i class="fas fa-chess-knight"></i>
  king <i class="fas fa-chess-king"></i>
 */


