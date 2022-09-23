@ignore
Feature: [FE] Vehicle types functionality

  Scenario Outline: Switching main car search vehicle types
    Given Open login page for vehicle types functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for vehicle types functionality
    Then Car search page for vehicle types functionality is displayed
    Then Enter <defaultOperationNumber> defaultOperationNumber and select car params and click on Vehicles found button
    And Check if car result list contains response
    Then Reset search for car vehicle types functionality
    Then Open moto searchmask and enter <defaultOperationNumber> defaultOperationNumber and select moto params and click on Vehicles found button
    And Check if moto result list contains response
    Then Reset search for moto vehicle types functionality
    Then Open truck searchmask and enter <defaultOperationNumber> defaultOperationNumber and select truck params and click on Vehicles found button
    And Check if truck result list contains response
    Then Reset search for truck vehicle types functionality
    Then Open camper searchmask and enter <defaultOperationNumber> defaultOperationNumber and select camper params and click on Vehicles found button
    And Check if camper result list contains response
    Then Reset search for camper vehicle types functionality
    Then Log out from application after vehicle types functionality
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |

  Scenario Outline: Switching truck searchmask vehicle types
    Given Open login page for vehicle types functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for vehicle types functionality
    Then Car search page for vehicle types functionality is displayed
    Then Open truck searchmask and switch truck vehicle types and check console logs
    Then Log out from application after vehicle types functionality
    Examples:
      | defaultEmail                              | defaultPassword |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        |