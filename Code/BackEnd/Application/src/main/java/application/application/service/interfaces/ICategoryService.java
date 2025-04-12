package application.application.service.interfaces;

import application.application.DTO.CategoryDTO;
import application.application.model.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(String name);
    List<Category> getAllCategories();
    List<CategoryDTO> getAllCategoriesDTO();
    Category getCategoryById(String id);
    CategoryDTO getCategoryDTOById(String id);
}
