package application.application.mapper;

import application.application.DTO.CartDTO;
import application.application.DTO.CartItemDTO;
import application.application.DTO.CategoryDTO;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.model.Category;
import application.application.repository.CartItemRepository;
import application.application.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class CartMapper {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private CartItemMapper cartItemMapper;

    public CartDTO cartToDTO(Cart cart, List<CartItem> cartItemList) {
        return CartDTO.builder()
                .id(cart.getId())
//                .appUser(cart.getAppUser())
                .cartItemDTOList(cartItemMapper.cartItemListToCartItemDTOList(cartItemList))
                .build();
    }

    public CartDTO cartToDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
//                .appUser(cart.getAppUser())
                .cartItemDTOList(cartItemMapper.cartItemListToCartItemDTOList(cartItemRepository.getCartItemsByCart_Id(cart.getId())))
                .build();
    }


}
