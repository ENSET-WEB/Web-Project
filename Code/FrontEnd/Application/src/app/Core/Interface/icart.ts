import { ICartItem } from './icart-item';

export interface ICart {
  id: string;
  cartItems: ICartItem[];
  totalPrice: number;
}
