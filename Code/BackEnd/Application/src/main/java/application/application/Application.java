package application.application;

import application.application.DTO.CategoryDTO;
import application.application.mapper.CategoryMapper;
import application.application.mapper.ProductMapper;
import application.application.model.Category;
import application.application.model.Product;
import application.application.repository.CategoryRepository;
import application.application.repository.ProductRepository;
import application.application.samples.CategorySamples;
import application.application.samples.ProductSamples;
import application.application.service.implementation.CategoryService;
import application.application.service.implementation.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner run(
            ProductService productService,
            CategoryService categoryService,
            CategoryRepository categoryRepository,
            ProductRepository productRepository
    ) {
        return args -> {
//            System.out.println("Application started");
//            categoryRepository.findAll().forEach(category -> {
//                System.out.println(CategoryMapper.categoryToDTO(category));
//            });
//
//            productRepository.findAll().forEach(product -> {
//                System.out.println(ProductMapper.productToDTO(product));
//            });
        };
    }
}
