package application.application.service;

import application.application.model.CartItem;
import application.application.model.Product;
import org.springframework.stereotype.Service;


public interface ICartItemService {
    CartItem addCartItem(Product product, Integer quantity);
}
