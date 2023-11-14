Feature: login
  Background:
    Given the user with email "test@test.com" and password "123456" in database

  Scenario: sign in succeeded
    When User enters email "test@test.com" and password "123456"
    Then the user is logged in

  Scenario: sign in not succeeded with incorrect email
    When User enters email "test1@test.com" and password "123456"
    Then the user is not logged in

  Scenario: sign in not succeeded with incorrect password
    When User enters email "test@test.com" and password "123"
    Then the user is not logged in

  Scenario: sign in not succeeded with both incorrect email and password
    When User enters email "test@te.com" and password "123"
    Then the user is not logged in
