package application.application.service;

import application.application.DTO.CartDTO;
import application.application.DTO.ProductDTO;
import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.model.Product;

public interface ICartService {
    CartDTO addProductToAppUserCart(AppUser appUser, Product product, Integer quantity);

    CartDTO addProductToAppUserCartById(String appUserID, Product product, Integer quantity);
    CartDTO addProductToAppUserCartById(String appUserID, ProductDTO productDTO, Integer quantity);
    CartDTO addProductToAppUserCartById(String appUserID, String productId, Integer quantity);

    CartItem addProductToCart(Cart cart, Product product, Integer quantity);

    CartItem addProductToCart(Cart cart, Product product);

    Cart getCartByAppUserName(String appUserName);

    Cart getCartByAppUser(AppUser appUser);

    Cart getCartByAppUserById(String appUserId);

    CartDTO getCartDTOByAppUser(AppUser appUser);

    CartDTO getCartDTOByAppUserId(String appUserId);
}
