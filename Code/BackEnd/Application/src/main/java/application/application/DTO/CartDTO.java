package application.application.DTO;

import application.application.model.AppUser;
import application.application.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private String id;
//    private AppUser appUser;
    private List<CartItemDTO> cartItemDTOList;
}
