package application.application.mapper;


import application.application.DTO.ProductDTO;
import application.application.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    static public ProductDTO productToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .categoryName(product.getCategory().getName())
                .build();
    }

    static public List<ProductDTO> productListToDTOList(List<Product> productList) {
        return productList.stream()
                .map(ProductMapper::productToDTO).toList();
    }
}
