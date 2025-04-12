package application.application.service.interfaces;

import application.application.DTO.ProductDTO;
import application.application.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    List<ProductDTO> getAllProductsDTO();
    Product addProduct(String name, String description, double price, String imageUrl, String categoryName);
    Product addProduct(Product noCategoryProduct, String categoryName);
    Product getProductById(String id);
    ProductDTO getProductDTOById(String id);

}
