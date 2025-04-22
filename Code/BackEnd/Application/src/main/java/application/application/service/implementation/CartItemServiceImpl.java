package application.application.service.implementation;

import application.application.DTO.CartItemDTO;
import application.application.mapper.CartItemMapper;
import application.application.mapper.ProductMapper;
import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.repository.AppUserRepository;
import application.application.repository.CartItemRepository;
import application.application.repository.CartRepository;
import application.application.service.ICartItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements ICartItemService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private AppUserRepository appUserRepository;
    private CartItemMapper cartItemMapper;
    private ProductMapper productMapper;

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

    @Override
    @Transactional
    public CartItemDTO deleteCartItemById(String cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart Item not found"));
        cartItemRepository.delete(cartItem);
        return cartItemMapper.cartItemToDTO(cartItem);
    }

    @Override
    public CartItem updateCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItemTobeEdited = cartItemRepository.findById(cartItemDTO.getId()).orElseThrow(() -> new RuntimeException("Cart Item not found"));
        cartItemTobeEdited.setQuantity(cartItemDTO.getQuantity());
        return cartItemRepository.save(cartItemTobeEdited);

    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
}
