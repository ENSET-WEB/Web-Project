package application.application.controller;

import application.application.DTO.CartDTO;
import application.application.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private ICartService cartService;

    @GetMapping("/{id}")
    public CartDTO getCartByAppUserId(@PathVariable String id) {
        return cartService.getCartDTOByAppUserId(id);
    }
}
