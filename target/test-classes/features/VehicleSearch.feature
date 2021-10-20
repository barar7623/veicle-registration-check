@Vehicle
Feature: Search for Car details

  Scenario: CheckCar page title
   Given user is on car check application page
   When user gets the title of the page
   Then page title should be "Car Tax Check | Free Car Check"

  Scenario: Search for car details
    Given user navigated to carCheck application
    When user gets Vehicle Registration number from input text file
    Then User should able see car details

