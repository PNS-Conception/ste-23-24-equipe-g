Feature: Order Delivery Confirmation

  Scenario: Delivery person delivers the order
    Given an order is "ASSIGNED" to a delivery person
    When the delivery person finalizes the delivery
    And the user confirms receipt on their phone
    Then the order status should be "DELIVERED"
    And the delivery person's status should be "AVAILABLE"

  Scenario: Delivery person delivers without user confirmation
    Given an order is "ASSIGNED" to a delivery person
    And the user cannot confirm receipt
    When the delivery person finalizes the delivery without user confirmation
    Then the order should require a signature and identity verification
    And the order status should be "DELIVERED"
    And the delivery person's status should be "AVAILABLE"
