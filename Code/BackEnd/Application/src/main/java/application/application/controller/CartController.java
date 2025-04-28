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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private ICartService cartService;
    private ICartItemService cartItemService;
    private CartItemMapper cartItemMapper;

    @GetMapping("/{appUserId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CartDTO> getCartByAppUserId(@PathVariable String appUserId) {
        return new ResponseEntity<>(cartService.getCartDTOByAppUserId(appUserId), HttpStatus.OK);
    }

    @GetMapping("/{appUserId}/size")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Integer> getCartItemsSize(@PathVariable String appUserId) {
        Integer cartItemsSize = cartService.getCartDTOByAppUserId(appUserId).getCartItemDTOList().size();
        return new ResponseEntity<Integer>(cartItemsSize, HttpStatus.OK);
    }

    @PostMapping("/addCartItem")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CartDTO> addProductToAppUserCart(
            @RequestParam String appUserId,
            @RequestParam String productId,
            @RequestParam(name = "quantity", defaultValue = "1") Integer quantity
    ) {
        return new ResponseEntity<>(cartService.addProductToAppUserCartById(appUserId, productId, quantity), HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteCartItem")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CartItemDTO> deleteProductFromAppUserCart(@RequestParam String cartItemId) {
        return new ResponseEntity<>(cartItemService.deleteCartItemById(cartItemId), HttpStatus.OK);
    }

    @PutMapping("/updateCartItem")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CartItemDTO> updateProductFromAppUserCart(@RequestBody CartItemDTO cartItemDTO) {
        CartItemDTO updatedCartItem = cartItemMapper.cartItemToDTO(cartItemService.updateCartItem(cartItemDTO));
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }

}
