Feature: [FE] Searchmask make model dropdown duplicate values

  Scenario Outline: Searchmask make dropdown duplicate values
    Given Open login page for make model dropdown duplicate values
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for make model dropdown duplicate values
    Then Car search page for make model dropdown duplicate values is displayed
    And Check if make dropdown contains no duplicates
    Then Log out from application after make model dropdown duplicate values
    Examples:
      | defaultEmail                              | defaultPassword |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        |

  Scenario Outline: Searchmask model dropdown duplicate values
    Given Open login page for make model dropdown duplicate values
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for make model dropdown duplicate values
    Then Car search page for make model dropdown duplicate values is displayed
    And Check if model dropdown contains no duplicates
    Then Reset search for make model dropdown
    Then Log out from application after make model dropdown duplicate values
    Examples:
      | defaultEmail                              | defaultPassword |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        |