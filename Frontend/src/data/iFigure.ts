import {Teams} from './teams';

export interface IFigure{
  getOwner(): Teams;
  getType(): string;
}
