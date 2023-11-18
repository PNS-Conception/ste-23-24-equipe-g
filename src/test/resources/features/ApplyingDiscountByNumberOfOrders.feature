Feature: Applying Discount by number of orders


  Scenario: Discount is applied when number of orders is 10
    Given "Alice" has ordered 9 items from restaurant "Bob's Pizza"
    And "Bob's Pizza" is offering a discount of 5% on the 10th order
    When "Alice" orders 1 dish from "Bob's Pizza" for 10 and pays
    Then the order total should be "9.5"
    And the number of orders should be set to 0 for "aAlice"

  Scenario: Discount is not applied when number of orders is less than 10
    Given "Alice" has ordered 8 items from restaurant "Bob's Pizza"
    And "Bob's Pizza" is offering a discount of 5% on the 10th order
    When "Alice" orders 1 dish from "Bob's Pizza" for 10 and pays
    Then the order total should be "10"
    And the number of orders should be set to 9 for "Alice"