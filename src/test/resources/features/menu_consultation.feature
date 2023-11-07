
Feature: Consultation des Menus
  As an internet user, I want to browse menus from different campus restaurants.

  Scenario: Viewing a List of Restaurants
    Given I am an internet user
    When I visit the campus food ordering system
    Then I should see a list of all available restaurants on campus

  Scenario: Viewing a Restaurant's Menu
    Given I am on the list of campus restaurants
    When I select a restaurant
    Then I should see the full menu offered by that restaurant

  Scenario: Menu Details
    Given I am viewing a restaurant's menu
    When I look at a specific dish
    Then I should see the dish's name, ingredients, and price
