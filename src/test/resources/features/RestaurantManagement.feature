# Created by caleb at 05/11/2023
Feature: Restaurant Schedule and Menu Update

  As a restaurant manager, I want to update the opening hours and menu offerings so that customers can see current information and make choices accordingly.

  Background:
    Given the manager is logged into the management system

  Scenario: Successful update of opening hours by the manager
    When the manager updates the opening hours to Monday from 10 AM to 10 PM for "Chez Gourmet"
    Then the system confirms that the new opening hours have been saved

  Scenario: Immediate publication of the new opening hours
    When a user views the "Chez Gourmet" restaurant page
    Then the new opening hours should be immediately visible

  Scenario: Manager updates menu information
    When the manager modifies the "Classic Burger" to include vegetarian options
    And submits the changes
    Then the system confirms the menu has been updated
    And the "Classic Burger" should now display the new ingredient options

  Scenario: Adding a new dish to the menu
    When the manager adds a new dish "Caesar Salad" with details to the menu
    Then the system confirms the addition of the new dish
    And the "Caesar Salad" should be visible to users on the menu

  Scenario: Indicating the availability of a menu item
    Given "Chez Gourmet" has a limited capacity of 20 orders for the special dish "Pan-Seared Foie Gras"
    And 20 orders have already been placed for this dish
    When a user views the "Pan-Seared Foie Gras" on the menu
    Then it should be marked as "Unavailable"

  Scenario: Preventing order of an unavailable dish
    Given the "Pan-Seared Foie Gras" is no longer available
    When a user attempts to order this dish
    Then the system should prevent adding the dish to the order
    And inform the user that the dish is no longer available
