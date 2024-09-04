package steps;


import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utility.AllureReportGenerator;
import utility.WebDriverActions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Hooks extends WebDriverActions {

    @BeforeAll
    public static void beforeAll() {
        try {
            File allureResults = new File("allure-results");
            if (allureResults.exists()) {
                FileUtils.cleanDirectory(allureResults);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        getDriver();

    }


    @After
    public void failedScreenshort(Scenario scenario) {

        if (scenario.isFailed()) {

            System.out.print("Scenario");

            String scenarioName = scenario.getName();

            System.out.println("Scenario Name :" + scenarioName);

            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            int i = 0;
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
            try {
                System.out.println("While taking screenshot for scenario is failed");

                FileUtils.copyFile(screenshot, new File("target/Screenshots/" + scenarioName + " " + dateName + ".png"));


            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {

            System.out.println("Scenario Pass");
        }

    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        AllureReportGenerator.generateAllureReport();
    }

}



