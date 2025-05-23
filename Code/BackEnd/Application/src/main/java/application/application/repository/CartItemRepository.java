package application.application.repository;

import application.application.model.Cart;
import application.application.model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> getCartItemsByCart_AppUser_Name(String name);

    List<CartItem> getCartItemsByCart_AppUser_Id(String id);

    List<CartItem> getCartItemsByCart_Id(String cartId);

    List<CartItem> getCartItemsByCart(Cart cart);

    CartItem deleteCartItemById(String id);

}
