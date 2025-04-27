import { ICartItem } from "./icart-item";


export interface ICart {
  id: string;
  cartItemDTOList: ICartItem[];
  // totalPrice: number;
}
