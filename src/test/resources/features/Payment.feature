Feature: Pay for an order

  Background:
    Given a client "Alice"
    And having a cart of 1 "pizza" and 1 "pasta" price 20€ and 10€

  Scenario: Pay for an order of a pizza and a pasta
    When Alice orders a pizza and a pasta
    And Alice pays 30€
    Then the order's status should be "PAID"
    And Alice's cart should be empty
    And Alice's orders should have an order with 1 pizza and 1 pasta and a total of 30€

  Scenario: Attempt to pay for an order of a pizza and a pasta but payment fails
    When Alice orders a pizza and a pasta
    And Alice attempts to pay
    Then the payment should fail
    And Alice's cart should still have 1 pizza and 1 pasta
    And the order's status should not be "PAID"

    Scenario:Pay for an order of a pizza and a pasta with balance of 10€
      When Alice orders a pizza and a pasta
      And Alice has a balance of 10€
      And Alice pays 20€
      Then the order's status should be "PAID"
      And the balance should be 0€


