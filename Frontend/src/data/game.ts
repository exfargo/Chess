import {User} from './user';
import {Move} from './move';

export interface Game {
  id: number;
  user1: User;
  user2: User;
  winner: User;
  moves: Move[];
}
