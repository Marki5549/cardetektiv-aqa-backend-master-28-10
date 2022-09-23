@ignore
Feature: [FE] Dat rating by vin code functionality

  Scenario Outline: Vehicle search by vin
    Given Open login page for vin search
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for vin search
    Then Car search page for vin search is displayed
    Then Open dat settings and save <datCustomerNumber> datCustomerNumber and <datUsername> datUsername and <datPassword> datPassword for vin search
    Then Open searchmask and enter <defaultOperationNumber> defaultOperationNumber and <vin> vin and <ez> ez and <kilometer> kilometer and open dat rating
    Then Dat rating window should contain vin
    And Clear dat credentials and reset search for vin search
    And Log out from application after vin search
    Examples:
      | defaultEmail                              | defaultPassword | datCustomerNumber | datUsername | datPassword | defaultOperationNumber | vin               | ez   | kilometer |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 1317322           | cardetektiv | (a7te4WC#   | 11111                  | VF7DEXTESTSTUB002 | 2011 | 123123    |