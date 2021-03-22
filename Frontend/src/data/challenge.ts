import {User} from './user';

export interface Challenge {
  id: number;
  challenger: User;
  accepted: boolean;
}
