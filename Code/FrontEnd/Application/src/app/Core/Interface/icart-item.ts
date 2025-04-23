import { IProduct } from "./iproduct";


export interface ICartItem {
  id: string;
  product: IProduct;
  quantity: number;
}
