@ignore
Feature: [FE] Account settings change password functionality

  Scenario Outline: Attempt to change password from account settings
    Given Open login page for change password functionality
    When Enter <userName> userName <oldPassword> oldPassword
    Then Car search page for change password functionality is displayed
    And Open settings for change password functionality
    And Enter <oldPassword> oldPassword <newPassword> newPassword twice and save and close settings
    Then Log out from application after change password functionality
    And Enter <userName> userName <newPassword> newPassword
    Then Authorization to application with new password must be successful
    And Log out after change password functionality
    Examples:
      | userName                                                | oldPassword | newPassword  |
      | ihor.martyniuk+change+password+searchmask@indealpro.com | Test0109    | Test%random% |