@ignore
Feature: [FE] Result list pdf generation

  Scenario Outline: Result list all ads list pdf
    Given Open login page for pdf generation
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for pdf generation
    Then Car search page for pdf generation is displayed
    And Enter pdf generation <defaultOperationNumber> defaultOperationNumber and select params and click on Vehicles found button
    Then Result list page for pdf generation is displayed
    Then Create all ads list pdf and check if all ads list pdf is displayed
    Then Open searchmask page for pdf generation
    And Reset search for pdf generation
    Then Log out from application after pdf generation
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |

  Scenario Outline: Result list all ads details pdf
    Given Open login page for pdf generation
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for pdf generation
    Then Car search page for pdf generation is displayed
    And Enter pdf generation <defaultOperationNumber> defaultOperationNumber and select params and click on Vehicles found button
    Then Result list page for pdf generation is displayed
    Then Create all ads details pdf and check if all ads details pdf is displayed
    Then Open searchmask page for pdf generation
    And Reset search for pdf generation
    Then Log out from application after pdf generation
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |