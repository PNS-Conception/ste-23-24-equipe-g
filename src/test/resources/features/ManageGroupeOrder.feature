Feature: Place Order Management

  Background:
    Given a order group with "Alice" the owner
    And Bob is a member of the order group



  Scenario: place group order all orders are paid
    When Alice has placed order from the restaurant "Vapiano" and paid for it
    And Bob has placed order form "le déclice" and paid for it
    Then the order group status should be Paid
    And the restaurants le déclice and Vapiano receive the order


  Scenario: place group order not all orders are paid
    When Alice has placed order from the restaurant "Vapiano" and paid for it
    And Bob has a place order form "le déclice" and not paid for it
    Then the order group status should be not paid
    And the restaurants does not receive the order


  Scenario: one order in the group is canceled
    Given group order is paid
    When Bob cancels his order
    Then the order group still should be Paid
    And Bob should not be a member of the order group
    And Bob should be refunded


  Scenario: all orders in the group are canceled
    Given group order is paid
    When all members cancels their order
    Then the order group status should be Canceled
    And all members should be refunded
    And the group should be deleted


  Scenario: group order is ready
    Given all sub orders are accepted
    When all sub orders are ready
    Then the order group status should be Ready
    And all members should be notified

  Scenario: group order is picked up
    Given group order is assigned
    When the delivery person validates group order
    Then the order group status should be Picked up
    And all members should be notified

  Scenario: group order is delivered
    Given group order is picked up
    When the delivery person delivers the order
    Then the order group status should be Delivered
    And all members should be notified
    And the group should be deleted
