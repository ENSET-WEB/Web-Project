package application.application.service.implementation;

import application.application.DTO.CategoryDTO;
import application.application.mapper.CategoryMapper;
import application.application.model.Category;
import application.application.repository.CategoryRepository;
import application.application.service.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
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
    public Category addCategory(Category category) {
        if (categoryRepository.findByName(category.getName()) != null)
            throw new RuntimeException("Category already exists");
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> addCategoryList(List<Category> categories) {
        categories.forEach(category -> {
            addCategory(category);
        });
        return categories;
    }

    @Override
    public List<Category> addCategories(List<String> categoryNames) {
        return categoryNames.stream().map(categoryName -> addCategory(categoryName)).toList();
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
