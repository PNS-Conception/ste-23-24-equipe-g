
Feature: Order Details and Notification

  Scenario: Delivery person receives order details
    Given an order has been assigned to a delivery person
    When the delivery person receives the order details
    Then the delivery person should have details including route, pickup time, restaurants, and delivery location

  Scenario: User receives delivery person details
    Given an order is assigned to a delivery person
    When the system sends the delivery details to the user
    Then the user should receive the delivery person's ID and phone number
