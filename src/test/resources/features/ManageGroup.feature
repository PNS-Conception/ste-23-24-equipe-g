Feature: Group Management

  Background: I am "Alice"


  Scenario: create group oder
    When "Alice" create a group order
    And Alice set the delivery address to "123 Main St, San Francisco, CA 94105"
    Then the group order should be created
    And Alice should be the owner of the group order
    And Alice should be the only member of the group order

  Scenario: add a member to a group order as a group owner
    Given a group is created
    And Alice is the owner of the group order add "Bob" to the group order
    Then the group order should have 2 members Alice and Bob


  Scenario: delete a member to a group order as a group member
    Given a group is created
    And Alice is the owner of the group order
    And Bob is a member of the group order
    When Alice delete Bob from the group order
    Then the group order should have 1 member Alice

  Scenario: a member can leave a group order
    Given a group is created
    And Alice is the owner of the group order
    And Bob is a member of the group order
    When Bob leave the group order
    Then the group order should have 1 member Alice
    And Bob should not be a member of the group order


  Scenario: the group owner can delete a group
    Given a group is created
    And Alice is the owner of the group order
    When Alice delete the group order
    Then the group order should be deleted
    And Alice should not be a member of the group order

  Scenario: the group owner can not delete a group after the group order is accepted
    Given a group is created
    And Alice is the owner of the group order
    And Bob is a member of the group order
    And the group order is paid
    When the group order is Accepted
    Then Alice can not delete the group order

  Scenario: the group owner can delete a group after the group order is rejected
    Given a group is created
    And Alice is the owner of the group order
    And Bob is a member of the group order
    And the group order is paid
    When the group order is Rejected
    Then Alice can delete the group order

  Scenario: the group owner leave the group before the group order is accepted and after "Alice" order is paid
    Given a group is created
    And Alice is the owner of the group order
    And Bob is a member of the group order
    And Sam is a member of the group order
    And the group order is placed
    When Alice leave the group order
    Then Alice order should be deleted from the group order
    And Alice should be refunded
    And Bob should be the owner of the group order


  Scenario: the group owner leave the group before the group before the "Alice" order is paid
    Given a group is created
    And Alice is the owner of the group order
    And Bob is a member of the group order
    And Sam is a member of the group order
    And the group order is placed
    When Alice leave the group order
    Then Alice order should be deleted from the group order
    And Alice should be refunded
    And Bob should be the owner of the group order
