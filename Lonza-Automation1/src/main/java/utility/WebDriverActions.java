package utility;


import java.io.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverActions implements WebDriver, JavascriptExecutor, HasCapabilities {
    public static final int TIME_OUT_TIME_FOR_ELEMENT = 10;
    public static WebDriver driver;


    public static void getDriver() {
        if (driver == null) {
            // Example: Initialize ChromeDriver
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        }
    }


    protected Actions getActions() {
        return new Actions(driver);
    }

    public void get(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    public Navigation navigate() {
        return driver.navigate();
    }

    public Options manage() {
        return driver.manage();
    }

    public Object executeScript(String s, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(s, args);
    }

    public Object executeAsyncScript(String s, Object... args) {
        return ((JavascriptExecutor) driver).executeAsyncScript(s, args);
    }

    public Capabilities getCapabilities() {
        return ((HasCapabilities) driver).getCapabilities();
    }

    /* Web Element Actions */

    protected boolean WaitForElementPresence(By byElement) throws Exception {
        int Counter = 0;
        boolean isPresence = false;
        while (findElements(byElement).isEmpty() && Counter < 3) {
            Thread.sleep(100);
            if (!findElements(byElement).isEmpty()) {
                isPresence = true;
                break;
            }
            Counter = Counter + 1;
        }
        if (!findElements(byElement).isEmpty()) {
            isPresence = true;
        }
        return isPresence;
    }

    protected boolean WaitForElementVisible(By byElement) throws Exception {
        int Counter = 0;
        boolean isPresence = false;

        while (!isPresence && Counter < 5) {
            try {
                if (!findElements(byElement).isEmpty()) {
                    if ((findElement(byElement)).isDisplayed()) {
                        System.out.println("Element present");
                        isPresence = true;
                        break;
                    }
                }
            } catch (Exception Exp) {
                System.out.println("Exp");
            }
            Thread.sleep(100);
            Counter = Counter + 1;
        }
        return isPresence;
    }

    protected boolean WaitForElementEnable(By byElement) throws Exception {
        int Counter = 0;
        boolean isPresence = false;

        while (Counter < 3) {
            try {
                if (WaitForElementVisible(byElement)) {
                    if ((findElement(byElement)).isEnabled()) {
                        isPresence = true;
                        break;
                    }
                }
            } catch (Exception Exp) {
                System.out.println("Element is still not enable");
            }
            Thread.sleep(500);
            Counter = Counter + 1;
        }
        return isPresence;
    }

    protected boolean ElementExists(By byElement) throws Exception {
        int Counter = 0;
        boolean isPresence = false;

        while (Counter < 3) {
            if (!findElements(byElement).isEmpty()) {
                isPresence = true;
                break;
            }
            Thread.sleep(100);
            Counter = Counter + 1;
        }
        return isPresence;
    }

    boolean WaitForElementDisable(By byElement, int Counter) throws Exception {
        boolean flag = false;

        while (Counter <= 3) {
            try {
                if (!findElement(byElement).isEnabled()) {
                    flag = true;
                    break;
                }
            } catch (Exception Exp) {
                System.out.println("Element is still enabled");
            }
            Thread.sleep(1000);
            Counter = Counter + 1;
        }
        return flag;
    }

    protected boolean WaitUntilInvisible(By byElement) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(byElement));
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    protected boolean WaitUntilInvisible(By byElement, int timeOutTime) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeOutTime))
                    .until(ExpectedConditions.invisibilityOfElementLocated(byElement));
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    protected void click(By ByElement) throws Exception {

        try {
            WebElement webElement = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_TIME_FOR_ELEMENT))
                    .until(ExpectedConditions.presenceOfElementLocated(ByElement));
            moveToElement(webElement);
            new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_TIME_FOR_ELEMENT))
                    .until(ExpectedConditions.visibilityOfElementLocated(ByElement));
            new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_TIME_FOR_ELEMENT))
                    .until(ExpectedConditions.elementToBeClickable(ByElement));
            webElement.click();
            Thread.sleep(500);
        } catch (InterruptedException interruptedException) {
            System.out.println(
                    "******************************* InterruptedException is caught *******************************");
            throw interruptedException;
        } catch (StaleElementReferenceException staleElementException) {
            System.out.println(
                    "******************************* StaleElementReferenceException is caught *******************************");
            throw staleElementException;
        } catch (Exception exp) {
            Thread.sleep(3000);
            findElement(ByElement).click();
            System.out.println(
                    "******************************* Fail to click, try click again *******************************");
        }
    }

    protected void moveToElement(By byElement) {
        new Actions(driver).moveToElement(findElement(byElement)).perform();
    }

    protected void moveToElement(WebElement byElement) {
        new Actions(driver).moveToElement(byElement).perform();
    }


    protected boolean clickable(By by) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void click(WebElement element) throws InterruptedException, StaleElementReferenceException {
        try {
            WebElement webElement = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_TIME_FOR_ELEMENT))
                    .until(ExpectedConditions.visibilityOf(element));
            moveToElement(webElement);
            webElement.click();
            Thread.sleep(500);
        } catch (InterruptedException interruptedException) {
            System.out.println(
                    "*******************************InterruptedException is caught*******************************");
            throw interruptedException;
        } catch (StaleElementReferenceException staleElementException) {
            System.out.println(
                    "*******************************StaleElementReferenceException is caught*******************************");
            throw staleElementException;
        }
    }

    public void Refresh() {
        driver.navigate().refresh();
    }


    protected void doubleClick(By byElement) {
        new Actions(driver).doubleClick(findElement(byElement)).perform();
    }

    protected void doubleClick(WebElement element) {
        new Actions(driver).doubleClick(element).perform();
    }

    protected void scrollToElement(WebElement toElement) throws Exception {
        ((JavascriptExecutor) (driver)).executeScript("arguments[0].scrollIntoView(true);",
                toElement);
        Thread.sleep(500);
    }

    protected void sendKeys(WebElement element, String keyValue) throws Exception {
        WebElement webElement = new WebDriverWait(driver, Duration.ofSeconds(45))
                .until(ExpectedConditions.visibilityOf(element));
        webElement.sendKeys(keyValue);
        Thread.sleep(500);
    }

    protected void inputText(By byElement, String text) throws Exception {
        WaitForElementPresence(byElement);
        click(byElement);
        sendKeys(findElement(byElement), Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE + text);
    }

    protected void inputTextWIthoutBackSpace(By byElement, String text) throws Exception {
        WaitForElementPresence(byElement);
        click(byElement);
        sendKeys(findElement(byElement), Keys.chord(Keys.CONTROL, "a") + text);
    }

    protected void switchToNewWindow() {
        Set<String> windows = getWindowHandles();
        for (String window : windows) {
            if (!window.equalsIgnoreCase(driver.getWindowHandle())) {
                switchTo().window(window);
            }
        }
    }

    public void printLogBrowser() {
        for (LogEntry log : driver.manage().logs().get(LogType.BROWSER).getAll()) {
            System.out.println(log.getMessage());
        }
    }

    public void printLogPerformance() {
        List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
        System.out.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");

        for (LogEntry entry : entries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }

    public String getText(By byElement) {
        return findElement(byElement).getText();
    }





}
