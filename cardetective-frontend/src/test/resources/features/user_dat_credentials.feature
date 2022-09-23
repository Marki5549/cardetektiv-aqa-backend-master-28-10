@ignore
Feature: [FE] User dat credentials functionality

  Scenario Outline: User is able to save valid dat credentials
    Given Open login page for dat credentials
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for dat credentials
    Then Car search page for dat credentials is displayed
    And Open dat settings and save valid <datCustomerNumber> datCustomerNumber and <datUsername> datUsername and <datPassword> datPassword
    Then Dat credentials should be saved
    And Clear dat credentials
    Then Log out from application after dat credentials
    Examples:
      | defaultEmail                              | defaultPassword | datCustomerNumber | datUsername | datPassword |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 1317322           | cardetektiv | (a7te4WC#   |

  Scenario Outline: User attempt to save invalid dat credentials should be forbidden
    Given Open login page for dat credentials
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for dat credentials
    Then Car search page for dat credentials is displayed
    And Open dat settings and save invalid <invalidDatCustomerNumber> invalidDatCustomerNumber and <invalidDatUsername> invalidDatUsername and <invalidDatPassword> invalidDatPassword
    Then Saving dat credentials should be forbidden
    And Clear dat credentials
    Then Log out from application after dat credentials
    Examples:
      | defaultEmail                              | defaultPassword | invalidDatCustomerNumber | invalidDatUsername     | invalidDatPassword |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 1234567                  | invalidUsername        | password123        |