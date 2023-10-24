Feature: validate an order


  Scenario: accept order for preparation successfully
    Given an order
    And its order with status placed
    When a the stuff accepte the order
    Then the status of the order is accepted

  Scenario: reject order
    Given an order
    When the status of the order is placed
    And the restaurant is full
    Then a cashier reject the order

  Scenario: validate the order for pick up
    Given an order
    When its order with status accepted
    And a cashier validate the order
    Then the status of the order is ready


  Scenario: order is picked up
    Given an order with status ready
    And the delivery person validate the pick up
    Then the status of the order is picked up



  Scenario: order is delivered
    Given an order with status picked up
    And the delivery person validate the delivery
    Then the status of the order is delivered and the order is closed
