@ignore
Feature: [FE] Url query params functionality

  Scenario Outline: Check the aviability to pass car params from url to searchmask
    Given Pass car params to application base url
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for query params
    Then Car search page with selected car params is displayed
    Then Reset search for query params
    And Log out from application after query params
    Examples:
      | defaultEmail                              | defaultPassword |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        |

  Scenario Outline: Check the aviability to pass dat euro code from url to searchmask
    Given Pass dat euro code to application base url
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for query params
    Then Car search page with selected params from dat euro code is displayed
    Then Reset search for query params
    And Log out from application after query params
    Examples:
      | defaultEmail                              | defaultPassword |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        |