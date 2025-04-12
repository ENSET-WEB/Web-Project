package application.application.mapper;

import application.application.DTO.CategoryDTO;
import application.application.model.Category;

import java.util.List;
import java.util.stream.Collectors;


public class CategoryMapper {
    static public CategoryDTO categoryToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    static public List<CategoryDTO> categoryListToCategoryDTOList(List<Category> categoryList) {
        return categoryList.stream().map(CategoryMapper::categoryToDTO).toList();
    }

}
