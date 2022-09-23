@ignore
Feature: [FE] User forgot password functionality

  Scenario Outline: User attempt to recover password
    Given User opens login page for password recovery
    When User clicks on forgot your password button
    Then User is displayed forgot password page
    When User enters <userName> userName for password recovery
    Then User is displayed confirm password recovery page
    And User resets his password from email address
    Then User is displayed change password page
    And User enters <password> password twice
    Then User is displayed your password has been successfully changed page
    And User clicks on sign in button on password is changed page
    Then User is displayed login page after password recovery
    When User enters <userName> userName and new <password> password
    Then User is displayed car search page after password recovery
    And User logs out from application after password recovery
    Examples:
      | userName                                          | password     |
      | ihor.martyniuk+change+password+test@indealpro.com | Test%random% |