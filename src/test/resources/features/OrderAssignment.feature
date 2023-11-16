Feature: Order Assignment to Delivery Person

  Scenario: Assign order to delivery person
    Given there is an order ready for delivery
    And a delivery person is available
    When the system assigns the order to the delivery person
    Then the order status should be ASSIGNED
    And the delivery person should be unavailable
