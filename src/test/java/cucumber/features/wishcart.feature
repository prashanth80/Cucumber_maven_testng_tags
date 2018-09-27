@WishCart
Feature: Wish list and Cart pane

  Scenario:
    Given Home page is loaded
    And WishList icon is located
    And Hover over WishList icon
    Then WishList pane is loaded

  Scenario:
    Given Home page is loaded
    And Cart icon is located
    And Hover over Cart icon
    Then Cart pane is loaded