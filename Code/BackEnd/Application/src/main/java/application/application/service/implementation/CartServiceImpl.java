package application.application.service.implementation;

import application.application.DTO.CartDTO;
import application.application.mapper.CartMapper;
import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.model.Product;
import application.application.repository.AppUserRepository;
import application.application.repository.CartItemRepository;
import application.application.repository.CartRepository;
import application.application.repository.ProductRepository;
import application.application.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements ICartService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private AppUserRepository appUserRepository;

    @Override
    public CartItem addProductToAppUserCart(AppUser appUser, Product product, Integer quantity) {
        appUserRepository.findById(appUser.getId()).orElseThrow(() -> new RuntimeException("User Not Found"));

        Cart cart = getCartByAppUser(appUser);
        return addProductToCart(cart, product, quantity);
    }

    @Override
    public CartItem addProductToCart(Cart cart, Product product, Integer quantity) {
        productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Product does not exist"));
        cartRepository.findById(cart.getId()).orElseThrow(() -> new RuntimeException("Cart does not exist"));

        CartItem cartItem = CartItem.builder()
                .product(product)
                .quantity((quantity == 0) ? 1 : quantity)
                .cart(cart).build();

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem addProductToCart(Cart cart, Product product) {
        productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Product does not exist"));
        cartRepository.findById(cart.getId()).orElseThrow(() -> new RuntimeException("Cart does not exist"));

        CartItem cartItem = CartItem.builder()
                .product(product)
                .quantity(1)
                .cart(cart).build();

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart getCartByAppUserName(String appUserName) {
        return cartRepository.findByAppUserName(appUserName);
    }

    @Override
    public Cart getCartByAppUser(AppUser appUser) {
        return cartRepository.findByAppUserName(appUser.getName());
    }

    @Override
    public Cart getCartByAppUserId(String appUserId) {
        return cartRepository.findByAppUserName(appUserId);
    }

    @Override
    public CartDTO getCartDTOByAppUser(AppUser appUser) {
        Cart cart = cartRepository.findByAppUser_Id(appUser.getId());
        List<CartItem> cartItemList = cartItemRepository.getCartItemsByCart_AppUser_Id(appUser.getId());
        return CartMapper.cartToDTO(cart, cartItemList);
    }

    @Override
    public CartDTO getCartDTOByAppUserId(String appUserId) {
        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(() -> new RuntimeException("User Not Found"));
        return getCartDTOByAppUser(appUser);
    }

}
