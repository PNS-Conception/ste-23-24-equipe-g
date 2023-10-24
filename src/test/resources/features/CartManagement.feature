Feature: Cart Management

  Background:
    Given I am a client "Kaleb"

  Scenario: add Item to cart
    Given I have an empty cart
    When I choose a "pasta" with 2 quantity
    Then cart contains 1 item "pasta" with 2 as the quantity

  Scenario: add a different Item to a non empty cart
    Given I have a cart that already contains 1 "pasta"
    When I choose a "pizza" with 1 quantity
    Then my cart contains 1 "pizza" and 1 "pasta"

  Scenario: validate the cart
    When I validate my cart of 3 "pizza"
    Then my order contains 3 "pizza"

  Scenario: update cart
    And I have a cart that already contains 2 "pizza"
    When I want to add 3 more "pizza"
    Then the quantity of "pizza" is set to 5 in the basket

  Scenario: remove from cart
    And I have a cart that already contains 10 "pasta"
    When I want to remove 3 "pasta"
    Then the quantity of "pasta" is set to 7 in the basket
