package application.application.mapper;

import application.application.DTO.CartDTO;
import application.application.DTO.CategoryDTO;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CartMapper {
    static public CartDTO cartToDTO(Cart cart, List<CartItem> cartItemList) {
        return CartDTO.builder()
                .id(cart.getId())
                .appUser(cart.getAppUser())
                .cartItemList(cartItemList)
                .build();
    }

//    static public List<CartDTO> cartListToCartDTOList(List<Cart> cartList) {
//        return cartList.stream().map().toList();
//    }

}
