Feature: Mnager service

  Background:
    Given manager of Restaurant logged in


  Scenario: Add Dish to empty menu
    Given a empty menu
    When the admin add dish with name "dish1" and price 10
    Then the dish should be in the menu of Restaurant


  Scenario: Add Dish to non empty menu
    Given a menu that contain dish with name "dish1" and price 10
    When the admin add dish with name "dish2" and price 10
    Then the "dish2" should be in the menu of Restaurant

  Scenario: Remove Dish
    When the admin remove dish with name "dish1"
    Then "dish1" should not be in the menu of Restaurant

  Scenario: add oping hours to empty schedule
    Given empty schedule
    When the admin add oping hours with day "Monday" from "10:00" to "20:00"
    Then "Monday" from "10:00" to "20:00" should be in the oping hours of Restaurant

  Scenario: add oping hours to non empty schedule
    Given schedule contain "Monday" from "10:00" to "20:00"
    When the admin add oping hours with day "Tuesday" from "10:00" to "20:00"
    Then "Monday" from "10:00" to "20:00" should be in the oping hours of Restaurant
    And "Tuesday" from "10:00" to "20:00" should be in the oping hours of Restaurant

  ##Scenario: add
## pas compris  ce point de capacité
