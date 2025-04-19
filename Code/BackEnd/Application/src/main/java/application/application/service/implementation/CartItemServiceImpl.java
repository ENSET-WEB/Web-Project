package application.application.service.implementation;

import application.application.model.CartItem;
import application.application.model.Product;
import application.application.service.ICartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements ICartItemService {

    @Override
    public CartItem addCartItem(Product product, Integer quantity) {
//        CartItem cartItem = CartItem.builder()
//                .product(product)
//                .quantity(quantity)
//                .build();
        return null;
    }
}
