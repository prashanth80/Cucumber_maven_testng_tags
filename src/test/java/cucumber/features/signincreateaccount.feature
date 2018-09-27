@LoginDrop
Feature: Sign in create account

  Scenario: Login pane is located
    Given Home page is loaded
    And Sign in icon is located
    And Hover over Sign in icon
    Then Sign in hover opens sign in panel
    Then Sign in is by default selected

  Scenario:
    Given Home page is loaded
    And Sign in icon is located
    And Hover over Sign in icon
    And click on CreateAccount label
    Then CreateAccount pane is loaded
