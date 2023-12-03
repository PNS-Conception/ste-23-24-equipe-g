
Feature: Any user can see the list of restaurants


  Scenario: User can see the list of restaurants
    Given I am a user on the homepage
    Then I should see "KFC"
    And I should see "McDonalds"
    And I should see "Burger King"


  Scenario: User can select a restaurant and see its menu
    Given I am a user on the homepage
    When I click on "KFC"
    Then I should see the menu
