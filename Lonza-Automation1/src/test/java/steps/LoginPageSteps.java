package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobject.Locators;
import utility.WebDriverActions;

public class LoginPageSteps extends WebDriverActions {


    @When("User have click the submit options")
    public void user_have_click_the_submit_options() throws Exception {
        click(Locators.submit_Submit);

    }

    @Then("User should verify the success message for user name and password and close the window")
    public void userShouldVerifyTheSuccessMessageForUserNameAndPasswordAndCloseTheWindow() {

        String expectedValue = "My Contacts: Title";


        String actualValue = getTitle();


        System.out.print(actualValue);

        Assert.assertEquals(actualValue, expectedValue);


    }
}
