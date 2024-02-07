package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import stepdefinitions.BaseStepDefinitions
import utils.PageObjectManager
import utils.PropertyReader

class StepDefinitions extends BaseStepDefinitions {
  static PropertyReader propertyReader

  @Given("The user navigated to the application login page")
  void launchUrl() {
    propertyReader = new PropertyReader()
    launchURL(propertyReader.getPropertyByName("url"))
  }

  @When("The login page is displayed the user enters his credentials and presses the login button")
  static void userFillsInLogInCredentials() {
    PageObjectManager.getLogInPage()
    PageObjectManager.loginPage.logIn()
  }

  @Then("Validate that the user is on the main page of products")
  static void verifySuccessfulLogin() {
    PageObjectManager.getLogInPage()
    PageObjectManager.loginPage.isLoginSuccessful()
  }

  @Then("Logout from the application")
  static void signOut() {
    PageObjectManager.getLogInPage()
    PageObjectManager.loginPage.logOut()
  }
}
