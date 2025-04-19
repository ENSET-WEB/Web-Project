package application.application.service.implementation;

import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.repository.AppUserRepository;
import application.application.repository.CartItemRepository;
import application.application.repository.CartRepository;
import application.application.service.ICartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements ICartItemService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private AppUserRepository appUserRepository;

    //    Later: Use proper Exception Management
    @Override
    public List<CartItem> getCartItemListByAppUserId(String appUserId) {
        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(() -> new RuntimeException("User not found"));
        return cartItemRepository.getCartItemsByCart_AppUser_Id(appUser.getId());
    }

    @Override
    public List<CartItem> getCartItemListByAppUserName(String appUserName) {
        AppUser appUser = appUserRepository.findByName(appUserName);
        if (appUser == null) throw new RuntimeException("User not found");
        return cartItemRepository.getCartItemsByCart_AppUser_Id(appUser.getId());
    }

    @Override
    public List<CartItem> getCartItemListByAppUser(AppUser appUser) {
        appUserRepository.findById(appUser.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        return cartItemRepository.getCartItemsByCart_AppUser_Id(appUser.getId());
    }

    @Override
    public List<CartItem> getCartItemListByCart(Cart cart) {
        return cartItemRepository.getCartItemsByCart(cart);
    }

    @Override
    public List<CartItem> getCartItemListByCartId(String cartId) {
        return cartItemRepository.getCartItemsByCart_Id(cartId);
    }
}
