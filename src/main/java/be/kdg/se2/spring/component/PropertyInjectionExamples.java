package be.kdg.se2.spring.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Templates for injecting properties from application.properties or application.yml.
 *
 * Place in: src/main/java/be/kdg/se2/templates/spring/component/
 *
 * Example application.properties:
 * app.name=My Application
 * app.version=1.0.0
 * app.max-connections=100
 * app.timeout=30000
 * app.enabled=true
 * app.allowed-hosts=localhost,127.0.0.1,example.com
 */
@Component
public class PropertyInjectionExamples {

    /**
     * Simple string property injection.
     */
    @Value("${app.name}")
    private String applicationName;

    /**
     * Property with default value (used if property not found).
     */
    @Value("${app.version:1.0.0}")
    private String version;

    /**
     * Integer property injection.
     */
    @Value("${app.max-connections:10}")
    private int maxConnections;

    /**
     * Long property injection.
     */
    @Value("${app.timeout:5000}")
    private long timeout;

    /**
     * Boolean property injection.
     */
    @Value("${app.enabled:true}")
    private boolean enabled;

    /**
     * List property injection (comma-separated values).
     */
    @Value("${app.allowed-hosts}")
    private List<String> allowedHosts;

    /**
     * Array property injection.
     */
    @Value("${app.admin-roles:ADMIN,SUPER_ADMIN}")
    private String[] adminRoles;

    /**
     * System property injection.
     */
    @Value("#{systemProperties['user.home']}")
    private String userHome;

    /**
     * Environment variable injection.
     */
    @Value("#{systemEnvironment['PATH']}")
    private String systemPath;

    /**
     * SpEL expression injection.
     */
    @Value("#{T(java.lang.Math).random() * 100}")
    private double randomNumber;

    /**
     * Property placeholder with expression.
     */
    @Value("${app.base-url:http://localhost}:${server.port:8080}")
    private String fullUrl;

    // Getters
    public String getApplicationName() { return applicationName; }
    public String getVersion() { return version; }
    public int getMaxConnections() { return maxConnections; }
    public long getTimeout() { return timeout; }
    public boolean isEnabled() { return enabled; }
    public List<String> getAllowedHosts() { return allowedHosts; }
}

