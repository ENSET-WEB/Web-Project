package application.application.controller;

import application.application.DTO.CategoryDTO;
import application.application.service.implementation.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategoriesDTO();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable String id) {
        return categoryService.getCategoryDTOById(id);
    }
}
