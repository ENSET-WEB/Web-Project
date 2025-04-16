package application.application.samples;

import application.application.model.Category;
import application.application.service.ICategoryService;
import application.application.service.implementation.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategorySamples {
    private static ICategoryService categoryService;

    public static List<Category> returnSamples() {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().name("Guitar").build());
        categories.add(Category.builder().name("Basses").build());
        categories.add(Category.builder().name("Keys").build());
        categories.add(Category.builder().name("Drums").build());
        return categories;
    }

    public static void addSamples() {
        List<Category> categories = returnSamples();
        categoryService.addCategoryList(categories);
    }
}
