Feature: Buffet Order Management

  Scenario: Verifying the Buffet Issa Nissa Order
    Given the predefined buffets are initialized
    When we select the "Buffet Issa Nissa"
    Then the order should be for "30" people with Issa Nissa items

  Scenario: Validating Issa Nissa buffet contents
    Given the predefined buffets are initialized
    When we select the "Buffet Issa Nissa"
    Then the buffet should contain specific Issa Nissa dishes

  Scenario: Validating number of people, number of formules, and total price in a Buffet Order
    Given the predefined buffets are initialized
    When we select the "Buffet Issa Nissa"
    Then the order should be for "30" people
    And the buffet should contain "10" formules
    And the total price should be correctly calculated
    And the total price should be "300" euros

  Scenario: Validating delivery details of a Buffet Order
    Given the predefined buffets are initialized
    When we select the "Buffet Issa Nissa"
    And we set the delivery location to "Main University Hall" and the contact person to "John Doe"
    Then the delivery location should be "Main University Hall"
    And the contact person for delivery should be "John Doe"

  Scenario: Cancelling a Buffet Order
    Given the predefined buffets are initialized
    When we select the "Buffet Issa Nissa"
    And the order is cancelled
    Then the status of the order should be definitively "Cancelled"
    And no charges should be applied

  Scenario: Modifying a Buffet Order for a University Event
    Given "Alice" from university staff has ordered a "Buffet Mignardises" for "30" people for a university event
    When she changes the order to "Buffet HotPizza" for "40" people
    Then the buffet order should be updated to the "Buffet HotPizza" formula for "40" people
    And the updated order details should be confirmed with Alice