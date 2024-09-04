@smoke
Feature: Launching a  signup page


  Scenario Outline:Launching new application in chrome
    Given  User have launch the url sample "<URL>"
    When User have click the signup options
    Then User should verify the success message and close the window
    Examples:
      | URL |
      | url |


