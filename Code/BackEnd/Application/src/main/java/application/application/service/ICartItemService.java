package application.application.service;

import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICartItemService {
    List<CartItem> getCartItemListByAppUserId(String appUserId);
    List<CartItem> getCartItemListByAppUserName(String appUserName);
    List<CartItem> getCartItemListByAppUser(AppUser appUser);

    List<CartItem> getCartItemListByCart(Cart cart);

    List<CartItem> getCartItemListByCartId(String cartId);
}
