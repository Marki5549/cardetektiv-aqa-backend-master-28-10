@ignore
Feature: [FE] Archive search functionality

  Scenario Outline: Archive search vehicles found functionality
    Given Open login page for archive search
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for archive search
    Then Car search page for archive search is displayed
    And Open archive search and enter <defaultOperationNumber> defaultOperationNumber and select params and click on Buy report button
    Then Result list page for archive search is displayed
    And Check if result list response correspond to selected params
    Then Open archive search page
    And Log out from application after archive search
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | %random%               |

  Scenario Outline: Archive search car detailed functionality
    Given Open login page for archive search
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for archive search
    Then Car search page for archive search is displayed
    And Open archive search and enter <defaultOperationNumber> defaultOperationNumber and select params and click on Buy report button
    Then Result list page for archive search is displayed
    And Check aviability of result list response car detailed params
    Then Open archive search page
    And Log out from application after archive search
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | %random%               |

  Scenario Outline: Archive search car preview functionality
    Given Open login page for archive search
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for archive search
    Then Car search page for archive search is displayed
    And Open archive search and enter <defaultOperationNumber> defaultOperationNumber and select params and click on Show Preview
    Then Preview for archive search should be displayed
    And Log out from application after archive search
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | %random%               |