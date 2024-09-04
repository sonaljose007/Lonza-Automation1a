# QA_Automation

## Requirements

  Following the requirements for running this project:

    Java  22.0.2 Remember to add a envirnoment variable JAVA_HOME and update the existing variable PATH
    Apache Maven 3.9.8 Remember to add MVN_HOME environment variable, and add MVN_HOME\bin to the existing variable PATH

## Project Structure
  Short description for project folder:

    pageObject: It includes all page locators, all defined as public static BY.

    utility:  includes several utility classes designed to simplify common tasks and promote code reusability

    src/main/java/resources:  It contains key configuration file used to manage environment-specific settings for Cucumber project.

    runner:  The test runner class with TestNg annotations to execute the tests.

    steps:  It contains the Java methods that are mapped to the textual steps in feature file. The methods need to annotated with one of the Cucumber annotations and the annotated value should contain a regex pattern that matches the textual step

    src/test/resources:  contains the feature file describing the scenarios in Gherkin syntax.

    pom.xml: It contains information about the project and configuration details used by Maven to build the project.

    target:  The directory where compiled files and reports are generated.

    allure-results: The directory where Allure report results are stored.

    allure-report: The directory where Allure HTML reports are generated after processing the results.

## How to run and debug test in Local
  Run a feature:
    Execute in terminal[Current in project folder]:

    mvn clean test




