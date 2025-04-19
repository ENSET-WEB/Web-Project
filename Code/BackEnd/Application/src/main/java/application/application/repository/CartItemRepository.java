package application.application.repository;

import application.application.model.Cart;
import application.application.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> getCartItemsByCart_AppUser_Name(String name);
    List<CartItem> getCartItemsByCart_Id(String cartId);
}
