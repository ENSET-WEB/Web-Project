package application.application.service;

import application.application.DTO.CartDTO;
import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.model.Product;

public interface ICartService {
    CartItem addProductToAppUserCart(AppUser appUser, Product product, Integer quantity);
    CartItem addProductToCart(Cart cart, Product product, Integer quantity);
    CartItem addProductToCart(Cart cart, Product product);
    Cart getCartByAppUserName(String appUserName);
    Cart getCartByAppUser(AppUser appUser);

    Cart getCartByAppUserId(String appUserId);

    CartDTO getCartDTOByAppUser(AppUser appUser);

    CartDTO getCartDTOByAppUserId(String appUserId);
}
