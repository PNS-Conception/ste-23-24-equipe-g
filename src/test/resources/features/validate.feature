Feature: Validate an order

  Scenario: Accept order for preparation successfully
    Given I have an order with status "PLACED"
    When the staff accepts the order
    Then the status of the order should be "ACCEPTED"

  Scenario: Reject order
    Given I have an order with status "PLACED"
    And the restaurant is full
    When the cashier rejects the order
    Then the status of the order should be "REJECTED"

  Scenario: Validate the order for pick up
    Given I have an order with status "ACCEPTED"
    When the cashier validates the order
    Then the status of the order should be "READY"

  Scenario: Order is assigned to a delivery person
    Given I have an order with status "READY"
    When the staff validates the pick up
    Then the status of the order should be "ASSIGNED"

  Scenario: Order is picked up
    Given I have an order with status "ASSIGNED"
    When the delivery person validates the pick up
    Then the status of the order should be "PICKEDUP"

  Scenario: Order is delivered
    Given I have an order with status "PICKEDUP"
    When the delivery person validates the delivery
    Then the status of the order should be "DELIVERED"
    And the order should be closed
