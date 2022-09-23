@ignore
Feature: [FE] Searchmask vehicles found functionality

  Scenario Outline: Searchmask bmw fourth series
    Given Open login page for vehicles found functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for vehicles found functionality
    Then Car search page for vehicles found functionality is displayed
    And Enter <defaultOperationNumber> defaultOperationNumber and select bmw fourth series params and click on Vehicles found button
    Then Result list page for vehicles found functionality is displayed
    And Check if result list response correspond to bmw fourth series
    Then Reset search for vehicles found functionality
    Then Log out from application after vehicles found functionality
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |

  Scenario Outline: Searchmask cupra ateca
    Given Open login page for vehicles found functionality
    When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for vehicles found functionality
    Then Car search page for vehicles found functionality is displayed
    And Enter <defaultOperationNumber> defaultOperationNumber and select cupra ateca params and click on Vehicles found button
    Then Result list page for vehicles found functionality is displayed
    And Check if result list response correspond to cupra ateca
    Then Reset search for vehicles found functionality
    Then Log out from application after vehicles found functionality
    Examples:
      | defaultEmail                              | defaultPassword | defaultOperationNumber |
      | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |

  Scenario Outline: Searchmask kia stinger
      Given Open login page for vehicles found functionality
      When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for vehicles found functionality
      Then Car search page for vehicles found functionality is displayed
      And Enter <defaultOperationNumber> defaultOperationNumber and select kia stinger params and click on Vehicles found button
      Then Result list page for vehicles found functionality is displayed
      And Check if result list response correspond to kia stinger
      Then Reset search for vehicles found functionality
      Then Log out from application after vehicles found functionality
      Examples:
        | defaultEmail                              | defaultPassword | defaultOperationNumber |
        | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |

  Scenario Outline: Searchmask skoda octavia
      Given Open login page for vehicles found functionality
      When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for vehicles found functionality
      Then Car search page for vehicles found functionality is displayed
      And Enter <defaultOperationNumber> defaultOperationNumber and select skoda octavia params and click on Vehicles found button
      Then Result list page for vehicles found functionality is displayed
      And Check if result list response correspond to skoda octavia
      Then Reset search for vehicles found functionality
      Then Log out from application after vehicles found functionality
      Examples:
        | defaultEmail                              | defaultPassword | defaultOperationNumber |
        | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |

  Scenario Outline: Searchmask tesla model s
      Given Open login page for vehicles found functionality
      When Enter <defaultEmail> defaultEmail and <defaultPassword> defaultPassword for vehicles found functionality
      Then Car search page for vehicles found functionality is displayed
      And Enter <defaultOperationNumber> defaultOperationNumber and select tesla model s params and click on Vehicles found button
      Then Result list page for vehicles found functionality is displayed
      And Check if result list response correspond to tesla model s
      Then Reset search for vehicles found functionality
      Then Log out from application after vehicles found functionality
      Examples:
        | defaultEmail                              | defaultPassword | defaultOperationNumber |
        | ihor.martyniuk+expert+stage@indealpro.com | Test0109        | 11111                  |