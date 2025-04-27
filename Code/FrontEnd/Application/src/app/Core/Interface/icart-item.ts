import { IProduct } from './iproduct';

export interface ICartItem {
  id: string;
  productDTO: IProduct;
  quantity: number;
}

// {
//   "id": "b36d32a0-ed44-4023-afd0-1d904b3bbb97",
//   "cartItemDTOList": [
//     {
//       "id": "032e24b6-689d-45c7-b7b8-bbc8406f41d5",
//       "quantity": 1234,
//       "productDTO": {
//         "id": "7ee473ae-5f10-4098-a91c-60c3b0634ee9",
//         "name": "Yamaha MODX6",
//         "description": "A powerful 61-key synthesizer with FM and AWM2 sound engines for rich and expressive tones.",
//         "price": 1699,
//         "imageUrl": "https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/MODX6-large.jpg",
//         "categoryName": "Keys"
//       }
//     },
