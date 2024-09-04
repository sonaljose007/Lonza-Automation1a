@smoke
Feature: Launching new application


  Scenario Outline:Launching new application in chrome
    Given  User have launch the url sample "<URL>"
    When User have click the submit options
    Then User should verify the success message for user name and password and close the window
    Examples:
      | URL |
      | url |


