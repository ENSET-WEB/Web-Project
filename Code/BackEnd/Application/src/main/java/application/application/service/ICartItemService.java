package application.application.service;

import application.application.DTO.CartItemDTO;
import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.model.CartItem;

import java.util.List;


public interface ICartItemService {
    List<CartItem> getCartItemListByAppUserId(String appUserId);
    List<CartItem> getCartItemListByAppUserName(String appUserName);
    List<CartItem> getCartItemListByAppUser(AppUser appUser);

    List<CartItem> getCartItemListByCart(Cart cart);

    List<CartItem> getCartItemListByCartId(String cartId);

//    CartItem deleteCartItemByCartItem(CartItem cartItem);

    CartItemDTO deleteCartItemById(String cartItemId);

//    CartItem updateCartItem(CartItem cartItem);

    CartItem updateCartItem(CartItemDTO cartItemDTO);

    List<CartItem> getAllCartItems();
}
