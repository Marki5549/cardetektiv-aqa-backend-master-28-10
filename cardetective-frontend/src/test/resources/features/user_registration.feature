@ignore
Feature: [FE] User registration functionality

  Scenario Outline: Step by step user registration attempt
    Given User opens login page for registration attempt
    When User clicks on sign up button
    Then User is displayed register page
    When User enters <email> email and <secretFieldForTests> secretFieldForTests and selects branch and product for registration and confirms agreement
    Then User is displayed confirm registration page
    And User confirms his email address
    Then User is displayed let's set up your account page
    And User enters <firstName> firstName and <lastName> lastName
    Then User is displayed tell us about your business page
    And User enters <nameOfBusiness> nameOfBusiness and <plz> plz and selects country
    Then User is displayed you almost did it page
    And User selects honorific and enters <phone> phone and <whereDidYouHearAboutUs> whereDidYouHearAboutUs
    Then User is displayed you did it page
    And User clicks on sign in button
    Then User is displayed login page
    When User enters <email> email and <password> password
    Then User is authorized to application after successful registration
    And User logs out from application after successful registration
    Examples:
      | email                                             | password | secretFieldForTests     | firstName | lastName  | nameOfBusiness | plz   | phone         | whereDidYouHearAboutUs |
      | ihor.martyniuk+dealer+test+%random%@indealpro.com | Test6666 | LSyrGNGwmN7HTFvh39eWdry | Ihor      | Martyniuk | indealpro      | 01327 | +380673804170 | indealpro              |
