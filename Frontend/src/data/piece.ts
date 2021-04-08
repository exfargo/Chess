import {Teams} from './teams';

export class Piece {

  constructor(private name: string, private htmlClass: string, private team: Teams) {
  }

  getName(): string {
    return this.name;
  }

  getHtmlClass(): string {
    let output: string = this.htmlClass;
    if (this.team !== Teams.Empty) {
      // fuj enumy to neumi
      // enum.tostring vraci index toho enumu a ne ten text

      if (this.team.toString() === 'Black') {
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

  setTeam(value: Teams): void{
    this.team = value;
  }
  setName(value: string): void{
    this.name = value;
  }
  setHtmlClass(value: string): void{
    this.htmlClass = value;
  }
}
