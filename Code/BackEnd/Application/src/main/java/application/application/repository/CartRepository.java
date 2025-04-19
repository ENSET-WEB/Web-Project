package application.application.repository;

import application.application.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByAppUserName(String appUserName);
    Cart findByAppUser_Id(String appUserId);
}
