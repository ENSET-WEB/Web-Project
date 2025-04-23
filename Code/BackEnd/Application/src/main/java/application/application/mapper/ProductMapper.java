package application.application.mapper;


import application.application.DTO.ProductDTO;
import application.application.model.Product;
import application.application.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductMapper {
    private ProductRepository productRepository;

    public ProductDTO productToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .categoryName(product.getCategory().getName())
                .build();
    }

    public List<ProductDTO> productListToDTOList(List<Product> productList) {
        return productList.stream()
                .map(this::productToDTO).toList();
    }

     public Product dtoToProduct(ProductDTO productDTO) {
        return productRepository.getById(productDTO.getId());
    }
}
