Feature: Admin service

  Background:
    Given admin logged in


  Scenario: Add restaurant
    When the admin add restaurant with name "Restaurant 1" and address "Address 1" and a restaurant manager with email "manager@manager.fr"
    Then "Restaurant 1" should be in the list of restaurants with address "Address 1" and manager "manager@manager.fr"


  Scenario: Add delivery person
    When the admin add delivery person with email "delivey@delivey.com"
    Then "delivey@delivey.com" should be in the list of delivery persons