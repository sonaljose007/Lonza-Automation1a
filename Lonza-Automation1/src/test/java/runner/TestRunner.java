package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/Features",
        glue = "steps", plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@smoke", dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests {

}


