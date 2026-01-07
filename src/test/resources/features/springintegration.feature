# ============================================================================
# SPRING INTEGRATION FEATURE FILE
# Place in: src/test/resources/features/spring-integration.feature
# ============================================================================

Feature: Spring Integration
  As a developer
  I want to test Spring-managed components
  So that I can verify dependency injection works correctly

  Background:
    Given the service is initialized

  Scenario: Service dependency injection
    When the user performs action "test"
    Then the service should respond correctly

  Scenario: Repository operations
    When data is persisted
    Then the data should be retrievable

  Scenario: Context sharing between steps
    Given I create a user with username "alice"
    When the user submits a request
    Then the response should contain the username

  Scenario: Multiple Spring beans
    Given I have session data
    When optional feature is used
    Then the session should be valid