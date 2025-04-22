package application.application;

import application.application.repository.*;
import application.application.samples.AppRoleSamples;
import application.application.samples.CategorySamples;
import application.application.samples.ProductSamples;
import application.application.service.IAppUserService;
import application.application.service.ICartItemService;
import application.application.service.ICartService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.List;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner run(

    ) {
        return args -> {
        };
    }

//    @Bean
    CommandLineRunner AddSampleData(CategorySamples categorySamples, ProductSamples productSamples, AppRoleSamples appRoleSamples) {
        return args ->
        {
            appRoleSamples.addSamples();
            categorySamples.addSamples();
            productSamples.addSamples();

        };
    }
}
