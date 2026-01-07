package be.kdg.se2.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Component scanning tells Spring where to look for @Component classes.
 * <p>
 * Spring Boot does this automatically, but you can customize it.
 */
@Configuration
@ComponentScan(basePackages = {
        "be.kdg.se2.spring.basics",
        "be.kdg.se2.spring.config"
})
public class ComponentScanConfiguration {

    /**
     * You can also use @ComponentScan filters to include/exclude specific classes.
     */
    // Example: @ComponentScan(
    //     basePackages = "be.kdg.se2.spring",
    //     includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
    //     excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Test.*")
    // )
}
