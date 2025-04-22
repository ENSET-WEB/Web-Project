package application.application.mapper;

import application.application.DTO.CartDTO;
import application.application.DTO.CartItemDTO;
import application.application.model.Cart;
import application.application.model.CartItem;
import application.application.model.Product;
import application.application.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class CartItemMapper {
    private CartItemRepository cartItemRepository;
    private ProductMapper productMapper;

    public CartItemDTO cartItemToDTO(CartItem cartItem) {
        return CartItemDTO.builder()
                .id(cartItem.getId())
                .productDTO(productMapper.productToDTO(cartItem.getProduct()))
                .quantity(cartItem.getQuantity())
                .build();
    }

//    Add Exception later
    public CartItem dtoToCartItem(CartItemDTO cartItemDTO) {
        return cartItemRepository.findById(cartItemDTO.getId()).get();
    }

    public List<CartItemDTO> cartItemListToCartItemDTOList(List<CartItem> cartItemList) {
        return cartItemList.stream().map(cartItem -> cartItemToDTO(cartItem)).collect(Collectors.toList());
    }
}
