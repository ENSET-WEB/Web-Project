package application.application.service;

import application.application.DTO.CategoryDTO;
import application.application.model.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(String categoryName);
    Category addCategory(Category category);
    List<Category> addCategoriesbyNameList(List<String> categoryNames);
    List<Category> addCategoriesbyCategoryList(List<Category> categories);
    List<Category> getAllCategories();
    List<CategoryDTO> getAllCategoriesDTO();
    Category getCategoryById(String id);
    CategoryDTO getCategoryDTOById(String id);
}
