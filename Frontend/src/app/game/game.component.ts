import {Component, ElementRef, OnInit} from '@angular/core';
import {Piece} from '../../data/piece';
import {Teams} from '../../data/teams';
import {Observable} from 'rxjs';
import {IFigure} from '../../data/iFigure';
import {ActivatedRoute} from '@angular/router';
import {GameService} from '../services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  constructor(private readonly route: ActivatedRoute, private gameService: GameService) {
  }

  // wtf co je za pojmenovani checkboard
  // i blame karel
  checkboard: Piece[][];
  selectedTile: Element;
  board: ElementRef;
  selectedX: number;
  selectedY: number;
  id: number;
  // asi se potom bude nejak brat z apicka??
  playingTeam: Teams = Teams.White;

  ngOnInit(): void {
    this.route.params.subscribe(
      u => this.id = u.id,
      e => console.log(e)
    );
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

  clickedTile(clickEvent: MouseEvent, clickedPiece: Piece, y: number, x: number): void {
    if (clickedPiece.getTeam() === this.playingTeam) {
      this.selectNewTile(clickEvent, x, y);
    } else if (this.selectedTile !== undefined) {
      this.makeMove(this.selectedX, this.selectedY, x, y);
    }
  }

  selectNewTile(clickEvent: MouseEvent, x: number, y: number): void {
    this.unselectTile();
    // select new tile
    this.selectedTile = (clickEvent.target as Element);
    // fix aby se vybralo celi policko a ne jenom ta figurka
    if (this.selectedTile.tagName !== 'TD') {
      this.selectedTile = this.selectedTile.parentElement;
    }
    this.selectedX = x;
    this.selectedY = y;
    this.selectedTile.classList.add('selected-tile');
  }

  unselectTile(): void {
    if (this.selectedTile !== undefined) {
      this.selectedTile.classList.remove('selected-tile');
      this.selectedTile = undefined;
      this.selectedX = undefined;
      this.selectedY = undefined;
    }
  }

  makeMove(sx: number, sy: number, tx: number, ty: number): void {
    console.log('attempted move');
    console.log('source :' + sx + ',' + sy);
    console.log('target :' + tx + ',' + ty);
    this.unselectTile();
  }

  updateBoard(board: Observable<IFigure[][]>): void{
    board.subscribe(s => {this.replaceBoard(s); }, error => {console.log('lmfao'); });
  }
  replaceBoard(board: IFigure[][]): void{
    for (let x = 0; x < 8; x++){
      for (let y = 0; y < 8; y++){
        const newTile: IFigure = board[x][y];
        const tile: Piece = this.checkboard[x][y];
        tile.setName(newTile.type);
        tile.setTeam(newTile.owner);
        tile.setHtmlClass('fas fa-chess-' + newTile.type.toLowerCase());
      }
    }
  }
  reloadGame(): void{
    this.updateBoard(this.gameService.getBoard(this.id));
  }
}

/*
  rook <i class="fas fa-chess-rook"></i>
  queen <i class="fas fa-chess-queen"></i>
  pawn <i class="fas fa-chess-pawn"></i>
  knight <i class="fas fa-chess-knight"></i>
  king <i class="fas fa-chess-king"></i>
 */


