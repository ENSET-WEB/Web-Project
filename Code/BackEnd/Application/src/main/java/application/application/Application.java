package application.application;

import application.application.model.AppRole;
import application.application.model.AppUser;
import application.application.repository.*;
import application.application.samples.AppRoleSamples;
import application.application.samples.CategorySamples;
import application.application.samples.ProductSamples;
import application.application.service.IAppUserService;
import application.application.service.ICartItemService;
import application.application.service.ICartService;
import application.application.service.implementation.AppRoleServiceImpl;
import application.application.service.implementation.AppUserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;


@SpringBootApplication
public class Application {

    private final AppUserRepository appUserRepository;

    public Application(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

        @Bean
    CommandLineRunner run(AppRoleRepository appRoleRepository, AppRoleServiceImpl appRoleServiceImpl, AppUserServiceImpl appUserServiceImpl, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
//            AppUser appUser = appUserServiceImpl.getAppUserByName("RHCP");
//            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
//            appUserRepository.save(appUser);
//            List<AppRole> appRole = appRoleRepository.findAll();
//            appUserRepository.save(AppUser.builder().name("ADMIN").email("ADMIN@mail.com").password(passwordEncoder.encode("ADMIN")).appRole(appRole).build());
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
