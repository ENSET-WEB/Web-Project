package application.application.controller;

import application.application.DTO.CartDTO;
import application.application.DTO.CartItemDTO;
import application.application.mapper.CartItemMapper;
import application.application.mapper.CartMapper;
import application.application.model.CartItem;
import application.application.service.ICartItemService;
import application.application.service.ICartService;
import jakarta.transaction.Transactional;
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
    private CartMapper cartMapper;
    private CartItemMapper cartItemMapper;

    @GetMapping("/{appUserId}")
    public ResponseEntity<CartDTO> getCartByAppUserId(@PathVariable String appUserId) {
        return new ResponseEntity<>(cartService.getCartDTOByAppUserId(appUserId), HttpStatus.OK);
    }

    @PostMapping("/addCartItem")
    public ResponseEntity<CartDTO> addProductToAppUserCart(
            @RequestParam String appUserId,
            @RequestParam String productId,
            @RequestParam(name = "quantity", defaultValue = "0") Integer quantity
    ) {
        return new ResponseEntity<>(cartService.addProductToAppUserCartById(appUserId, productId, quantity), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCartItem")
    public ResponseEntity<CartItemDTO> deleteProductFromAppUserCart(@RequestParam String cartItemId) {
        return new ResponseEntity<>(cartItemService.deleteCartItemById(cartItemId), HttpStatus.OK);
    }

    @PutMapping("/updateCartItem")
    public ResponseEntity<CartItemDTO> updateProductFromAppUserCart(@RequestBody CartItemDTO cartItemDTO) {
        CartItemDTO updatedCartItem = cartItemMapper.cartItemToDTO(cartItemService.updateCartItem(cartItemDTO));
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }

}
