@ignore
Feature: [FE] Dealer search functionality

  Scenario Outline: Check the working capacity of dealer search and saving results to branches and groups
    Given Open login page for dealer search functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for dealer search functionality
    Then Car search page for dealer search functionality is displayed
    Then Open dealer search
    And Enter <autohouse> autohouse and click on search button and dealer search results should be displayed and add results to branches and groups
    Then Open branches page and refresh it and check if results are available
    Then Open groups page and refresh it and check if results are available
    Then Clear dealer search
    And Log out from application after dealer search functionality
    Examples:
      | defaultEmail                              | defaultPassword | autohouse |
      | ihor.martyniuk+dealer+stage@indealpro.com | Test0109        | bmw       |
