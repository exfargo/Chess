import {Teams} from './teams';

export class Piece {

  constructor(private name: string, private htmlText: string, private team: Teams) {
  }

  getName(): string {
    return this.name;
  }

  getHtml(): string {
    let output: string = this.htmlText;
    if (this.team !== Teams.Empty) {
      // fuj enumy to neumi
      // enum.tostring vraci index toho enumu a ne ten text
      if (this.team === Teams.Black) {
        output += ' black-piece';
      } else {
        output += ' white-piece';
      }
    }
    return output;
  }

  getTeam(): Teams {
    return this.team;
  }
}
