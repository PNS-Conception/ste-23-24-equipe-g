Feature: Validate an order

  Scenario: Accept order for preparation successfully
    Given I have an order with status "Placed"
    When the staff accepts the order
    Then the status of the order should be "Accepted"

  Scenario: Reject order
    Given I have an order with status "Placed"
    And the restaurant is full
    When the cashier rejects the order
    Then the status of the order should be "Cancelled"

  Scenario: Validate the order for pick up
    Given I have an order with status "Accepted"
    When the cashier validates the order
    Then the status of the order should be "Ready"

  Scenario: Order is picked up
    Given I have an order with status "Ready"
    When the delivery person validates the pick up
    Then the status of the order should be "PickedUp"

  Scenario: Order is delivered
    Given I have an order with status "PickedUp"
    When the delivery person validates the delivery
    Then the status of the order should be "Delivered"
    And the order should be closed
