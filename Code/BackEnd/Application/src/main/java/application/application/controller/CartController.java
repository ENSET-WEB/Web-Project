package application.application.controller;

import application.application.DTO.CartDTO;
import application.application.DTO.CartItemDTO;
import application.application.mapper.CartItemMapper;
import application.application.mapper.CartMapper;
import application.application.service.ICartItemService;
import application.application.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private ICartService cartService;
    private ICartItemService cartItemService;
    private CartItemMapper cartItemMapper;

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable String cartId) {
        return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.OK);
    }

    @PostMapping("{appUserId}/addCartItem")
    public ResponseEntity<CartDTO> addProductToAppUserCart(
            @PathVariable String appUserId,
            @RequestParam String productId,
            @RequestParam(name = "quantity", defaultValue = "1") Integer quantity) {
        System.out.println("appUserId: " + appUserId);
        System.out.println("productId: " + productId);
        System.out.println("quantity: " + quantity);
        return new ResponseEntity<>(cartService.addProductToAppUserCartById(appUserId, productId, quantity),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartItemId}/deleteCartItem")
    public ResponseEntity<CartItemDTO> deleteProductFromAppUserCart(@PathVariable String cartItemId) {
        return new ResponseEntity<>(cartItemService.deleteCartItemById(cartItemId), HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}/updateCartItem")
    public ResponseEntity<CartItemDTO> updateProductFromAppUserCart(
            @PathVariable String cartItemId,
            @RequestBody CartItemDTO cartItemDTO) {
        cartItemDTO.setId(cartItemId);
        CartItemDTO updatedCartItem = cartItemMapper.cartItemToDTO(cartItemService.updateCartItem(cartItemDTO));
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }

}
