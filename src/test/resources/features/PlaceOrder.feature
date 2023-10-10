Feature: place an order

  Background:
    Given order
    And  having 1 cookies "pasta" price 15
    And having 2 cookies "pizza" price 10

  Scenario: order is paid and validated
    Given order placed by client
    When the client pays the order with his credit amount 45
    Then the order status is placed
    And the amount of credit card became 10
    And the order is validated

