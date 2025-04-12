package application.application.samples;

import application.application.model.Category;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class CategorySamples {
    public static List<Category> returnSamples() {
        List<Category> categories = new ArrayList<>();

        categories.add(Category.builder().name("Guitar").build());
        categories.add(Category.builder().name("Basses").build());
        categories.add(Category.builder().name("Keys").build());
        categories.add(Category.builder().name("Drums").build());

        return categories;
    }
}
