package application.application.service.implementation;

import application.application.DTO.CategoryDTO;
import application.application.mapper.CategoryMapper;
import application.application.model.Category;
import application.application.repository.CategoryRepository;
import application.application.service.interfaces.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(String name) {
        Category category = categoryRepository.findByName(name);
        if (category != null) throw new RuntimeException("Category already exists");
        Category newCategory = Category.builder()
                .name(name)
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryDTO> getAllCategoriesDTO() {
        return CategoryMapper.categoryListToCategoryDTOList(getAllCategories());
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public CategoryDTO getCategoryDTOById(String id) {
        return CategoryMapper.categoryToDTO(getCategoryById(id));
    }
}
