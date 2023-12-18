Feature: place an order

  Background:
    Given a client "bob" with a cart
    And having 1 "pizza" and 1 "pasta" price 20
    And a restaurant "le délice"
    And "le délice" is open at 10:00 and close at 20:00


  Scenario: order is placed and restaurant is open
    Given the restaurant is open
    And client choose the address "rue de la paix"
    When the client create the order delivery time is 17:00 20|10|2022
    And placed at 16:10 20|10|2022
    Then the order status is placed
