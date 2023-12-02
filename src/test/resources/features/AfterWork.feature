
Feature: Afterwork Orders
  As a user ordering for an afterwork event
  I want to create afterwork orders
  So that I can organize afterwork events without immediate payment or delivery processes

  Background:
    Given there are restaurants offering afterwork menus

  Scenario: Creating an afterwork order
    Given user "John" is logged in as an organizer
    When he creates an afterwork order for 5 expected participants at "The Gourmet" restaurant
    Then the afterWork order should be created
    And John should be the organizer of the afterwork order
    And the afterwork order should be created for 5 participants


  Scenario: Viewing available afterwork menu
    Given user "John" is logged in as an organizer
    When he views the available menu for afterworks at "The Gourmet" restaurant
    Then the available afterwork menu is presented with details

  Scenario: Adding participants to an existing afterwork order
    Given an afterwork order is already created for 5 participants
    When "John" adds 2 more participants to the order
    Then the total number of participants for the afterwork order is now 7

  Scenario: Cancelling an afterwork order
    Given an afterwork order is already created
    When "John" cancels this afterwork order
    Then the order is marked as cancelled

