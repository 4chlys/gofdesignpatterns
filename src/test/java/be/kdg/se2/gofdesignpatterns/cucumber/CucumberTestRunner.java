package be.kdg.se2.gofdesignpatterns.cucumber;

import org.junit.platform.suite.api.*;

/**
 * Cucumber test runner using JUnit Platform.
 * This is the entry point for running BDD tests.
 *
 * Place in: src/test/java/be/kdg/se2/templates/cucumber/
 *
 * To run:
 * - From IDE: Run this class as JUnit test
 * - From Gradle: ./gradlew test
 * - From Maven: mvn test
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "be.kdg.se2.gofdesignpatterns.cucumber")
public class CucumberTestRunner {
    // This class remains empty - it's just a marker for JUnit Platform
}