package pages

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import stepdefinitions.BaseStepDefinitions
import utils.PropertyReader

class LoginPage extends BaseStepDefinitions{

  @FindBy(id = "user-name")
  WebElement userNameField

  @FindBy(xpath = "//input[@id='password']")
  WebElement passwordField

  @FindBy(id = "login-button")
  WebElement loginButton

  @FindBy(xpath = "//span[text()='Products']")
  WebElement productsHeader

  @FindBy(id = "react-burger-menu-btn")
  WebElement hamburgerMenu

  @FindBy(id = "logout_sidebar_link")
  WebElement logOutButton

  @FindBy(css = "h3")
  WebElement errorMessageText

  WebDriver driver

  LoginPage(WebDriver driver) {
    this.driver = driver
    PageFactory.initElements(driver, this)
  }

  /**
   * To login in the application by filling in user and password
   */
  void logIn(String userName, String password) {
    sendKeys(userNameField, userName)
    sendKeys(passwordField, password)
    clickElement(loginButton)
  }

  /**
   * Check if after login the page header is present and if text is as expected
   */
  void isLoginSuccessful() {
    isElementPresent(productsHeader)
    Assert.assertTrue("Expected header text to be: Products but got " + getText(productsHeader), getText(productsHeader).contains("Products"))
  }

  /**
   * Check if after login with invalid credentials the error message text is as expected
   * @param text The error message text
   */
  void verifyInvalidCredentialsErrorMessage(String text) {
    isElementPresent(errorMessageText)
    Assert.assertTrue("Expected header text to be: Products but got " + getText(errorMessageText), getText(errorMessageText).contains(text))
  }

  /**
   * Log out from the app
   */
  void logOut() {
    clickElement(hamburgerMenu)
    clickElement(logOutButton)
  }
}
