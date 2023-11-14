Feature: Sign in
  Background: users in db
    Given given users with email "test@test.com" and password "123456"

  Scenario: sign in succeeded
    When User enters email "test@test.com" and password "123456"
    Then the user is signed in
    And the "test@test.com" and "123456" are stored in the database

  Scenario: sign in not succeeded
    When User enters email "test@test.com" and password "123"
    And the email or password is incorrect
    Then the user is not signed up