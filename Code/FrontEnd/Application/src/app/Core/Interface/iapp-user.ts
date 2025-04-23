import { ICart } from './icart';
import { IAppRole } from './iapp-role';

export interface IAppUser {
  id: string;
  name: string;
  email: string;
  password: string;
  appRoles: IAppRole[];
  cart: ICart;
}
