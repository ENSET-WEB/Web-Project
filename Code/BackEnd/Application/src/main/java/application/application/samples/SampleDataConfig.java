package application.application.samples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("addSampleData")
@Slf4j
public class SampleDataConfig {

    @Bean
    public void initializeSampleData(CategorySamples categorySamples,
            ProductSamples productSamples,
            AppRoleSamples appRoleSamples) {
        log.info("Initializing sample data for development environment...");

        try {
            log.debug("Adding sample roles...");
            appRoleSamples.addSamples();

            log.debug("Adding sample categories...");
            categorySamples.addSamples();

            log.debug("Adding sample products...");
            productSamples.addSamples();

            log.info("Sample data initialization completed successfully");
        } catch (Exception e) {
            log.error("Error while initializing sample data: {}", e.getMessage(), e);
        }
    }
}