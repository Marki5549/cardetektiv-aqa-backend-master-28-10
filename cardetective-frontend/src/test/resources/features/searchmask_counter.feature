@ignore
Feature: [FE] Searchmask counter functionality

  Scenario Outline: Searchmask counter requests values correct count
    Given Open login page for counter functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for counter functionality
    Then Car search page for counter functionality is displayed
    And Enter counter requests <defaultOperationNumber> defaultOperationNumber and select params and click on Vehicles found button
    Then Result list page for counter functionality is displayed
    And Open counter to check if counter requests values calculated correctly
    Then Open searchmask page for counter functionality
    And Reset search for counter functionality
    Then Log out from application after counter functionality
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |

  Scenario Outline: Searchmask counter equal params operations values correct count
    Given Open login page for counter functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for counter functionality
    Then Car search page for counter functionality is displayed
    And Enter counter equal params operations <defaultOperationNumber> defaultOperationNumber and select params and click on Vehicles found button
    Then Result list page for counter functionality is displayed
    And Open counter to check if counter equal params operations values calculated correctly
    Then Open searchmask page for counter functionality
    And Reset search for counter functionality
    Then Log out from application after counter functionality
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |

  Scenario Outline: Searchmask counter different op numbers operations values correct count
    Given Open login page for counter functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for counter functionality
    Then Car search page for counter functionality is displayed
    And Enter counter different op nums operations <defaultOperationNumber> defaultOperationNumber and select params and click on Vehicles found button
    Then Result list page for counter functionality is displayed
    And Open counter to check if counter different params operations values calculated correctly
    Then Open searchmask page for counter functionality
    And Reset search for counter functionality
    Then Log out from application after counter functionality
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | %random%               |

  Scenario Outline: Searchmask counter different makes operations values correct count
    Given Open login page for counter functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for counter functionality
    Then Car search page for counter functionality is displayed
    And Enter counter different makes operations <defaultOperationNumber> defaultOperationNumber and select params and click on Vehicles found button
    Then Result list page for counter functionality is displayed
    And Open counter to check if counter different params operations values calculated correctly
    Then Open searchmask page for counter functionality
    And Reset search for counter functionality
    Then Log out from application after counter functionality
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | %random%               |

  Scenario Outline: Searchmask counter different models operations values correct count
    Given Open login page for counter functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for counter functionality
    Then Car search page for counter functionality is displayed
    And Enter counter different models operations <defaultOperationNumber> defaultOperationNumber and select params and click on Vehicles found button
    Then Result list page for counter functionality is displayed
    And Open counter to check if counter different params operations values calculated correctly
    Then Open searchmask page for counter functionality
    And Reset search for counter functionality
    Then Log out from application after counter functionality
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | %random%               |

  Scenario Outline: Searchmask counter pdf created values correct count
    Given Open login page for counter functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for counter functionality
    Then Car search page for counter functionality is displayed
    And Enter counter pdf created <defaultOperationNumber> defaultOperationNumber and select params and click on Vehicles found button
    Then Result list page for counter functionality is displayed
    Then Create all ads list pdf
    And Open counter to check if counter pdf created values calculated correctly
    Then Open searchmask page for counter functionality
    And Reset search for counter functionality
    Then Log out from application after counter functionality
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | %random%               |