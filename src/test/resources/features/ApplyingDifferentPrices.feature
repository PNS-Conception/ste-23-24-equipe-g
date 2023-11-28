Feature: Applying Different Prices

  Background:
    Given a Item contains 2 "Pizza" that costs 10 each for regular customer and 8 each for premium customer
    And another Item contains 1 "Coke" that costs 5 each for regular customer and 4 each for premium customer
    And from a restaurant "bob's Pizza"

    Scenario: Price for regular customer
      When the customer validates the cart
      Then the order total should be 25

    Scenario: Price for staff
        Given A staff
        When the staff validates the cart
        Then the order total should be 20

    Scenario: Price for manager
        Given A manager
        When the manager validates the cart
        Then the order total should be 20


    Scenario: Price for premium delivery person
        Given A delivery person
        When the delivery person validates the cart
        Then the order total should be 20


