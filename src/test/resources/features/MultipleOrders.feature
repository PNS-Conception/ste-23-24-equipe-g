Feature: Place Multiple Order Management


  Scenario: create multiple order
    When "Alice" create a multiple order
    And Alice set the delivery address to "123 Main St, San Francisco, CA 94105" to mulpiple order
    Then the multiple order should be created
    And Alice should be the owner of the multiple order


  Scenario: add a order to the multiple order
    Given alice create a order from restaurant "chinese restaurant"
    When she add a order to the multiple order
    Then the long of the list of orders should be 2 in multiple order


  Scenario: cancel a order from the multiple order
    Given the order is paid
    When Alice cancel the order
    Then the order should be canceled
    And the order should be removed from the multiple order
    And Alice should be refunded with order amount

  Scenario: place group order all orders are paid
    When Alice has placed order from the restaurant "Vapiano" and paid for it and added to multiple order
    And Alice has placed another order form "le déclice" and paid for it and added to multiple order
    Then the multiple order status should be paid
    And the restaurants le déclice and Vapiano receive the orders

  Scenario: every order get paid it will be sent to the restaurant
    Given the order is paid
    When the order is sent to the restaurant

  Scenario: make a multiple order not all orders are paid
    When Alice has placed order from the restaurant "Vapiano" and paid for it and added to multiple order
    And the other order from "le déclice" is not paid yet
    Then the multiple order status should be not paid
    And the first restaurant receive the order
    And the second restaurant does not receive the order

  Scenario: all orders in multiple order get are canceled
    Given all sub orders paid
    When the owner cancels all orders
    Then the multiple order status should be Canceled
    And owner should be refunded
    And the multiple order should be deleted


  Scenario: multiple order is picked up
    Given multiple order is ready
    When the delivery person validates multiple order
    Then the multiple order status should be Picked up
    And all members should be notified


  Scenario: multiple order is delivered
    Given multiple order is picked up
    When the delivery person delivers the multiple order
    Then the multiple order status should be Delivered
    And all members should be notified
    And the multiple order should be deleted