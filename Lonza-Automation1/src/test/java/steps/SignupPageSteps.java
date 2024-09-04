package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.Locators;
import utility.WebDriverActions;

import java.io.IOException;

import static utility.EnvironmentPropertiesReader.getProperties;


public class SignupPageSteps extends WebDriverActions {


    @Given("User have launch the url sample {string}")
    public void userHaveLaunchTheUrlSample(String URL) throws IOException {

        get(getProperties(URL));

    }

//when case
    @When("User have click the signup options")
    public void user_have_click_the_signup_options() throws Exception {


        click(Locators.signup_Button);


    }

    @Then("User should verify the success message and close the window")
    public void user_should_verify_the_success_message_and_close_the_window() throws Exception {

        String expectedValue = "Sign up to begin adding your contacts!";

        String actualValue = getText(Locators.successmessage_Signup);


        System.out.print(actualValue);




    }


}

