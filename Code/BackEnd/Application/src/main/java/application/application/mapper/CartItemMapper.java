package application.application.mapper;

import application.application.DTO.CartDTO;
import application.application.DTO.CartItemDTO;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CartItemMapper {
    static public CartItemDTO cartItemToDTO(CartItem cartItem) {
        return CartItemDTO.builder()
                .id(cartItem.getId())
                .productDTO(ProductMapper.productToDTO(cartItem.getProduct()))
                .quantity(cartItem.getQuantity())
                .build();
    }
}
