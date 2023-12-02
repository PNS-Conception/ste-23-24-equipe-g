Feature: Extension Report User

  Scenario: Assigning a First Rating to a Customer
    Given that it is the customer's first order
    When the delivery person rates the customer with a score of 5
    Then the customer's average rating becomes 5
    And the customer's number of ratings becomes 1

  Scenario: Impact of a Low Rating on the Customer's Average
    Given that the customer already has 1 rate of 3
    When the delivery person rates the customer with a score of 1
    Then the customer's average rating becomes 2

  Scenario: Impact of a High Rating on the Customer's Average
    Given that the customer already has 3 rate of 4
    When the delivery person rates the customer with a score of 5
    Then the customer's average rating becomes 4.25

