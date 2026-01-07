# Spring DI and Cucumber BDD Templates

This project provides comprehensive templates for Spring Dependency Injection and Cucumber BDD testing.

## 📁 Project Structure

```
src/
├── main/
│   └── java/
│       └── be/kdg/se2/templates/
│           ├── spring/
│           │   ├── config/
│           │   │   ├── AppConfiguration.java
│           │   │   ├── ProfileConfiguration.java
│           │   │   └── ConditionalConfiguration.java
│           │   ├── service/
│           │   │   ├── ServiceComponent.java
│           │   │   └── QualifierExamples.java
│           │   ├── repository/
│           │   │   └── RepositoryComponent.java
│           │   ├── component/
│           │   │   ├── UtilityComponent.java
│           │   │   ├── ScopeExamples.java
│           │   │   └── PropertyInjectionExamples.java
│           │   └── interfaces/
│           │       └── [Supporting interfaces]
│           └── gofdesignpatterns/
│               ├── behavioral/
│               ├── creational/
│               └── structural/
└── test/
    ├── java/
    │   └── be/kdg/se2/templates/cucumber/
    │       ├── CucumberTestRunner.java
    │       ├── CucumberSpringConfiguration.java
    │       ├── steps/
    │       │   ├── BasicStepDefinitions.java
    │       │   ├── ParameterizedStepDefinitions.java
    │       │   ├── DataTableStepDefinitions.java
    │       │   ├── SpringIntegrationStepDefinitions.java
    │       │   └── ContextUsageStepDefinitions.java
    │       ├── hooks/
    │       │   └── HookDefinitions.java
    │       └── context/
    │           └── ScenarioContext.java
    └── resources/
        └── features/
            ├── basic.feature
            ├── scenario-outline.feature
            ├── tagged-scenarios.feature
            └── spring-integration.feature
```

## 🔧 Spring Dependency Injection Templates

### Configuration Styles

1. **Java-based Configuration** (`AppConfiguration.java`)
    - `@Configuration` with `@Bean` methods
    - Method parameter injection
    - Conditional beans
    - Bean scopes and lifecycle

2. **Profile-based Configuration** (`ProfileConfiguration.java`)
    - Development, production, test profiles
    - Multiple profiles per bean
    - Profile exclusion

3. **Conditional Configuration** (`ConditionalConfiguration.java`)
    - Property-based conditions
    - Class presence/absence
    - Bean presence/absence
    - Custom conditions

### Component Scanning

1. **Service Components** (`ServiceComponent.java`)
    - `@Service` annotation
    - Constructor injection (recommended)
    - Lifecycle callbacks

2. **Repository Components** (`RepositoryComponent.java`)
    - `@Repository` annotation
    - CRUD operations template
    - Exception translation

3. **Utility Components** (`UtilityComponent.java`)
    - Generic `@Component` annotation
    - Helper classes

### Advanced Features

1. **Qualifiers** (`QualifierExamples.java`)
    - `@Primary` for default beans
    - `@Qualifier` for specific implementations
    - Multiple implementations

2. **Bean Scopes** (`ScopeExamples.java`)
    - Singleton (default)
    - Prototype
    - Request (web)
    - Session (web)
    - Application (web)

3. **Property Injection** (`PropertyInjectionExamples.java`)
    - `@Value` annotation
    - `@ConfigurationProperties`
    - Default values
    - Type conversion

## 🥒 Cucumber BDD Testing Templates

### Test Runner

- **CucumberTestRunner.java**: JUnit Platform integration
- **CucumberSpringConfiguration.java**: Spring Boot integration

### Step Definitions

1. **Basic Steps** (`BasicStepDefinitions.java`)
    - Given/When/Then/And/But steps
    - Simple assertions
    - Step reuse

2. **Parameterized Steps** (`ParameterizedStepDefinitions.java`)
    - String, Integer, Double, Boolean parameters
    - Multiple parameters
    - Custom parameter types
    - Regex patterns

3. **Data Tables** (`DataTableStepDefinitions.java`)
    - List of maps (most common)
    - List of lists
    - Single map
    - Key-value pairs
    - Object transformation

4. **Spring Integration** (`SpringIntegrationStepDefinitions.java`)
    - `@Autowired` in step definitions
    - Service injection
    - Repository injection

5. **Context Sharing** (`ContextUsageStepDefinitions.java`)
    - ScenarioContext for state sharing
    - Passing data between steps
    - Type-safe retrieval

### Hooks

**HookDefinitions.java** provides:
- `@Before` / `@After` hooks
- Tag-based hooks
- Execution order
- Scenario information access
- Step-level hooks

## 🚀 Usage

### Running Tests

```bash
# All tests
./gradlew test

# Only Cucumber tests
./gradlew cucumber

# Smoke tests
./gradlew cucumberSmoke

# Integration tests
./gradlew cucumberIntegration

# Fast tests
./gradlew cucumberFast

# Exclude WIP
./gradlew cucumberNotWip

# Custom tags
./gradlew test -Dcucumber.filter.tags="@smoke and @fast"
```

### Creating New Features

1. Create `.feature` file in `src/test/resources/features/`
2. Write Gherkin scenarios
3. Run tests - Cucumber will suggest step definitions
4. Implement step definitions in appropriate package

### Creating Spring Beans

1. Choose appropriate annotation:
    - `@Service` for business logic
    - `@Repository` for data access
    - `@Component` for utilities
    - `@Configuration` for Java config

2. Use constructor injection:
```java
@Service
public class MyService {
    private final MyRepository repository;
    
    public MyService(MyRepository repository) {
        this.repository = repository;
    }
}
```

3. Enable component scanning (auto-enabled in Spring Boot):
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

## 📝 Feature File Examples

### Basic Scenario
```gherkin
Scenario: User login
  Given a user with username "alice" exists
  When the user logs in with password "secret"
  Then the user should be authenticated
```

### Scenario Outline
```gherkin
Scenario Outline: Multiple logins
  Given a user with username "<user>" exists
  When the user logs in with password "<password>"
  Then the result should be "<result>"
  
  Examples:
    | user  | password | result  |
    | alice | secret   | success |
    | bob   | wrong    | failure |
```

### Data Tables
```gherkin
Scenario: Create users
  Given the following users:
    | username | email          | role  |
    | alice    | alice@test.com | admin |
    | bob      | bob@test.com   | user  |
  Then 2 users should exist
```

## 🏷️ Tag Usage

- `@smoke` - Quick smoke tests
- `@fast` - Fast-running tests
- `@slow` - Slow-running tests
- `@database` - Requires database
- `@integration` - Integration tests
- `@web` - Web/UI tests
- `@wip` - Work in progress (skip in CI)
- `@cleanup` - Needs special cleanup

Run tagged tests:
```bash
./gradlew test -Dcucumber.filter.tags="@smoke"
./gradlew test -Dcucumber.filter.tags="@smoke and not @slow"
./gradlew test -Dcucumber.filter.tags="@database or @web"
```

## 📊 Reports

After running tests, view reports at:
- JUnit: `build/reports/tests/test/index.html`
- Cucumber: `build/reports/cucumber/cucumber-report.html`

## 🔍 Best Practices

### Spring DI

1. **Prefer constructor injection** over field injection
2. **Use interfaces** for dependencies
3. **Keep beans focused** (Single Responsibility)
4. **Use `@Primary` or `@Qualifier`** for multiple implementations
5. **Use profiles** for environment-specific config
6. **Use `@ConfigurationProperties`** for grouped properties

### Cucumber BDD

1. **Keep scenarios focused** (one concept per scenario)
2. **Use Background** for common setup
3. **Use Scenario Outline** for data-driven tests
4. **Tag scenarios** for organization
5. **Share state via ScenarioContext** not static fields
6. **Make step definitions reusable**
7. **Use meaningful step names**
8. **Keep step definitions small**

## 📚 Additional Resources

- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Cucumber-JVM](https://github.com/cucumber/cucumber-jvm)
- [Gherkin Reference](https://cucumber.io/docs/gherkin/reference/)

## 🤝 Contributing

These templates are starting points. Adapt them to your specific needs:
- Add custom parameter types
- Create domain-specific step definitions
- Add project-specific configurations
- Extend with additional patterns

## 📄 License

These templates are provided as-is for educational purposes.