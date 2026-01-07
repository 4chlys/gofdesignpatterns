package be.kdg.se2.spring.config;

import be.kdg.se2.spring.component.PrototypeBean;
import be.kdg.se2.spring.controller.ControllerClass;
import be.kdg.se2.spring.interfaces.RepositoryInterface;
import be.kdg.se2.spring.interfaces.ServiceInterface;
import be.kdg.se2.spring.service.ConditionalService;
import be.kdg.se2.spring.service.DependentService;
import be.kdg.se2.spring.service.InitializableService;
import be.kdg.se2.spring.service.impl.PrimaryServiceImplementation;
import be.kdg.se2.spring.service.impl.SecondaryServiceImplementation;
import be.kdg.se2.spring.service.impl.ServiceImplementation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Template for Java-based Spring configuration.
 * Use @Configuration to define beans programmatically.
 *
 * Place in: src/main/java/be/kdg/se2/templates/spring/config/
 */
@Configuration
public class AppConfiguration {

    /**
     * Simple bean definition.
     * Bean name defaults to method name.
     *
     * @return Bean instance managed by Spring container
     */
    @Bean
    public ServiceInterface serviceBean() {
        return new ServiceImplementation();
    }

    /**
     * Bean with dependencies - Method parameter injection.
     * Spring automatically resolves and injects the parameter.
     *
     * @param service Injected by Spring
     * @return Controller with injected dependency
     */
    @Bean
    public ControllerClass controllerBean(ServiceInterface service) {
        return new ControllerClass(service);
    }

    /**
     * Conditional bean - Only created if condition is met.
     * Useful for environment-specific beans.
     *
     * @return Conditional service instance
     */
    @Bean
    @Conditional(CustomCondition.class)
    public ConditionalService conditionalService() {
        return new ConditionalService();
    }

    /**
     * Bean with custom name and qualifier.
     * Use when you have multiple beans of same type.
     *
     * @return Primary service implementation
     */
    @Bean(name = "customServiceName")
    @Primary
    public ServiceInterface primaryService() {
        return new PrimaryServiceImplementation();
    }

    /**
     * Alternative implementation with qualifier.
     *
     * @return Secondary service implementation
     */
    @Bean
    @Qualifier("secondary")
    public ServiceInterface secondaryService() {
        return new SecondaryServiceImplementation();
    }

    /**
     * Prototype scope bean - New instance per request.
     * Default scope is singleton.
     *
     * @return New prototype instance each time
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    /**
     * Bean with initialization logic.
     *
     * @return Initialized service
     */
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public InitializableService initializableService() {
        return new InitializableService();
    }

    /**
     * Bean that depends on another bean.
     *
     * @param repository Injected repository
     * @return Service with repository dependency
     */
    @Bean
    @DependsOn("repositoryBean")
    public
    DependentService dependentService(RepositoryInterface repository) {
        return new DependentService(repository);
    }
}