package application.application.repository;

import application.application.model.AppUser;
import application.application.model.Cart;
import application.application.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByAppUserName(String appUserName);
}
