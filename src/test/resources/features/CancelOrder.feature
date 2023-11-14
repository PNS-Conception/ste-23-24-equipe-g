Feature: cancelOrder

  Background:
    Given a client "bob" with order
    And having 1 "pizza" and 1 "pasta" price 20 delivery
    And a restaurant "le délice"
    And "le délice" is open at 10:00 and close at 20:00


Scenario: client cancel order before 30 minutes
    When the order is placed, paid, and accepted at 20:00
    And 30 minutes have passed
    And it is still accepted
    Then client can cancel order
    And the status of the order is cancelled


  Scenario: client cannot cancel order before 30 minutes
    When the order is placed, paid, and accepted at 12:00
    And 30 minutes have passed
    Then the client cannot cancel the order
    And the status of the order is still accepted

  Scenario: restaurant cancels order placed outside opening hours
    When the order is placed, paid, and accepted at 19:20
    And the current time is 20:01
    Then the restaurant can cancel the order
    And the status of the order is cancelled

  Scenario: restaurant cancels order within half an hour
    When the order is placed, paid, and accepted at 12:00
    And 15 minutes have passed
    Then the restaurant can cancel the order
    And the status of the order is cancelled

  Scenario: restaurant cannot cancel order after half an hour
    When the order is placed, paid, and accepted at 12:00
    And 30 minutes have passed
    Then the restaurant cannot cancel the order
    And the status of the order is still accepted

  Scenario: the order is cancelled by the restaurant and the user is refunded
    When the order is placed, paid, and accepted at 12:00
    Then the restaurant can cancel the order
    And the user is refunded

  Scenario: the order is cancelled by the user and the user is refunded
    When the order is placed, paid, and accepted at 12:00
    Then client can cancel order
    And the user is refunded
