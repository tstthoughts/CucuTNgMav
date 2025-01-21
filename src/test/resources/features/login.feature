Feature: Login functionality
##https://ultimateqa.com/dummy-automation-websites/
  @Smoke
  Scenario: User enters credentials and logs in
    Given user is on the home page
    Given user is on the "home" page
    When user enters "standard_user" on object "usernameField" on "HomePage" page
    And user enters "secret_sauce" on object "passwordField" on "HomePage" page
    And user clicks on object "loginButton" on "HomePage" page
#    When user waits for 5 seconds
    When user handles the alert by "accept" with text ""

#    Then user should be logged in successfully
