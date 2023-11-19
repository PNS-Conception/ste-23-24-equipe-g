Feature: Extension Discount



  Scenario: Discount is applied when number of orders is superior to 10
    Given "Bob's Pizza" is offering a discount of 10% on the 11th order and it lasts for 15 days
    And "Alice" has ordered 2 items from the restaurant
    And she have a valid discount
    When "Alice" orders 1 dish from the restaurant for 10 and pays
    Then the order total is 9
    And the number of orders set to 0 for "Alice"


  Scenario: Discount is not applied when the discount is not valid anymore
    Given "Bob's Pizza" is offering a discount of 10% on the 11th order and it lasts for 15 days
    When the 15 days have passed
    Then the discount is not applied anymore


  Scenario: Discount is prolonged
    Given "Bob's Pizza" is offering a discount of 10% on the 11th order and it lasts for 15 days
    When less than 15 days have passed
    Then the discount will be prolonged for 15 days
