package utility;

import java.io.IOException;

public class

AllureReportGenerator {

    public static void generateAllureReport() {
        try {
            // Generate the Allure report
            ProcessBuilder generateBuilder = new ProcessBuilder("cmd.exe", "/c", "allure generate --single-file allure-results --clean -o allure-report");
            generateBuilder.inheritIO();
            Process generateProcess = generateBuilder.start();
            generateProcess.waitFor(); // Wait for the report generation to complete
            // Open the Allure report
            ProcessBuilder openBuilder = new ProcessBuilder("cmd.exe", "/c", "allure open allure-report");
            openBuilder.inheritIO();
            Process openProcess = openBuilder.start();
            Thread.sleep(5000); // Wait for the report to open (adjust timing as necessary)
            // Terminate the open process
             openProcess.destroy();
            // Forcefully close the terminal
            new ProcessBuilder("cmd.exe", "/c", "taskkill /f /im cmd.exe").start();
            // Exit the Java program
            System.exit(0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
