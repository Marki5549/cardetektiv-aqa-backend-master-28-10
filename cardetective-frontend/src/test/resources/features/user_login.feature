@ignore
Feature: [FE] User login functionality

  Scenario Outline: User is able to login using valid credentials
    Given User opens login page for login functionality
    When User enters <defaultEmail> defaultEmail and <defaultPassword> defaultPassword
    Then User is successfully authorized to application
    Then User logs out from application after login functionality
    Examples:
      | defaultEmail                              | defaultPassword |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        |

  Scenario Outline: User login attempt with invalid credentials should be forbidden
    Given User opens login page for login functionality
    When User enters <invalidEmail> invalidEmail and <invalidPassword> invalidPassword
    Then Login attempt should be forbidden
    Examples:
      | invalidEmail      | invalidPassword |
      | invalid@email.com | password123     |

  Scenario: User is able to login using token
    Given User gets token using post request and generates url
    Then User is successfully authorized to application
    Then User logs out from application after login functionality
