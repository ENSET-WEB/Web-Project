package application.application.samples;

import application.application.model.Category;
import application.application.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CategorySamples {
    private ICategoryService categoryService;

    public static List<Category> returnSamples() {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().name("Guitar").build());
        categories.add(Category.builder().name("Bass").build());
        categories.add(Category.builder().name("Keys").build());
        categories.add(Category.builder().name("Drums").build());
        return categories;
    }

    public void addSamples() {
        List<Category> categories = returnSamples();
        categoryService.addCategoriesbyCategoryList(categories);
    }
}
