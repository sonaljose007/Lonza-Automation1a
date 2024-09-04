package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import static utility.WebDriverActions.driver;

public class Locators {

    public Locators() {
        PageFactory.initElements(driver, this);
    }


    public static By signup_Button = By.xpath("//button[@id='signup']");

    public static By successmessage_Signup = By.xpath("//p[contains(text(),'Sign')]");

    public static By submit_Submit = By.xpath("//button[@id='submit']");

    public static By successmessage_Submit = By.xpath("//p[contains(text(),'sign')]");

    //p[contains(text(),'sign up')]
}
