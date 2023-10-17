
Feature: Order management for a client

  Scenario: Logging in as a user
    Given I am on the login page
    When I log in as "Alice"
    Then I should be logged in as "Alice"

  Scenario: Viewing the list of restaurants
    Given I am logged in as "Alice"
    When I view the list of restaurants
    Then I should see the list of available restaurants

  Scenario: Selecting a restaurant
    Given I am viewing the list of restaurants
    When I select the restaurant "Le Délice"
    Then I should see the menu of the restaurant "Le Délice"

  Scenario: Adding items to the cart
    Given I am viewing the menu of the restaurant "Le Délice"
    When I add the item "Pizza Margherita" with a quantity of 1 to my cart
    And I add the item "Soda" with a quantity of 1 to my cart
    Then my cart should contain the item "Pizza Margherita" with a quantity of 1
    And my cart should contain the item "Soda" with a quantity of 1

  Scenario: Validating the cart
    Given I have items in my cart
    When I validate my cart
    Then an order should be created with the items from my cart
