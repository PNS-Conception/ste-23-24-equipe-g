Feature: Restaurant Capacity Management

  Scenario: Placing an Order Within Capacity Limits
    Given there is a restaurant named "Campus Delight"
    And the capacity for "Campus Delight" is set to 20 meals for the 12:00 PM slot on 20th November 2023
    When a user tries to place an order for 5 meals at "Campus Delight" for 12:00 PM on 20th November 2023
    Then the order should be successfully placed
    And the remaining capacity for "Campus Delight" at 12:00 PM on 20th November 2023 should be 15 meals

  Scenario: Attempting to Place an Order Beyond Capacity Limits
    Given there is a restaurant named "Campus Delight"
    And the capacity for "Campus Delight" is set to 2 meals for the 1:00 PM slot on 20th November 2023
    When a user tries to place an order for 3 meals at "Campus Delight" for 1:00 PM on 20th November 2023
    Then the order should not be placed
    And an error message "La capacité du restaurant est insuffisante pour cette commande" should be displayed
    And the remaining capacity for "Campus Delight" at 1:00 PM on 20th November 2023 should still be 0 meals
