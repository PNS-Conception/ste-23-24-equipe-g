Feature: cart

  Background:
    Given I am a client "X"

  Scenario: add Item to cart
    Given I have an empty cart
    When I choose a "pasta" cookie with 2 quantity
    Then cart contains 1 item "pasta" with 2 as the quantity

  Scenario: add a different Item to a non empty cart
    Given I have a cart that already contains 1 "pasta"
    When I choose a "pizza" cookie with 1 as the quantity
    Then my cart contains 3 "pizza" & 1 "pasta"

  Scenario: validate the cart
    When I validate my cart of 3 "pizza"
    Then my order contains 3 "pizza"

  Scenario: update cart
    And I have a cart that already contains 2 "pizza"
    When I want to add 3 more "pizza"
    Then the quantity of "pizza" cookie is set to 5 in the basket

  Scenario: remove from cart
    And I have a cart that already contains 10 "pasta"
    When I want to remove 3 "pasta"
    Then the quantity of "pasta" cookie is set to 7 in the basket