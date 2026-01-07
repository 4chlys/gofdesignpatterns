# ============================================================================
# BASIC CUCUMBER FEATURE FILE
# Place in: src/test/resources/features/basic.feature
# ============================================================================

Feature: Basic Functionality
  As a user
  I want to perform basic operations
  So that I can verify system behavior

  Scenario: Simple operation
    Given the system is in initial state
    When the user performs action "test"
    Then the result should be "Action performed: test"

  Scenario: Operation with parameters
    Given a user with username "alice" exists
    When the user submits request with parameter 42
    Then the operation should succeed

  Scenario: Multiple step types
    Given the system has 5 items
    When the operation is executed
    Then the operation should succeed
    And the system should contain 5 items

  Scenario: Data table usage
    Given the following users exist:
      | username | email            | role  |
      | alice    | alice@test.com   | admin |
      | bob      | bob@test.com     | user  |
    Then the system should contain 2 items

  Scenario: Doc string usage
    When the following data is submitted:
      """
      {
        "name": "Test",
        "value": 123
      }
      """
    Then the operation should succeed