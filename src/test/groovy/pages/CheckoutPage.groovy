package pages

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import stepdefinitions.BaseStepDefinitions

class CheckoutPage extends BaseStepDefinitions {

    @FindBy(css = ".checkout_info")
    WebElement checkoutInfoContainer

    @FindBy(id = "first-name")
    WebElement firstNameInput

    @FindBy(id = "last-name")
    WebElement lastNameInput

    @FindBy(id = "postal-code")
    WebElement postalCodeInput

    @FindBy(css = "h3")
    WebElement errorButton

    @FindBy(id = "continue")
    WebElement continueToCheckoutStep2Button

    WebDriver driver

    CheckoutPage(WebDriver driver) {
        this.driver = driver
        PageFactory.initElements(driver, this)
    }

    /**
     * Enter/Fill in the first name of the user
     * @param firstName
     */
    void fillInFirstName(String firstName) {
        sendKeys(firstNameInput, firstName)
    }

    /**
     * Enter/Fill in the last name of the user
     * @param lastName
     */
    void fillInLastName(String lastName) {
        sendKeys(lastNameInput, lastName)
    }

    /**
     * Enter/Fill in the postal code of the user
     * @param postalCode
     */
    void fillInPostalCode(String postalCode) {
        sendKeys(postalCodeInput, postalCode)
    }

    /**
     * Checks if the error message is present and then gets the error message text
     * @return returns the error message as a String
     */
    String getErrorMessageText() {
        isElementPresent(errorButton)
       String errorText =  getText(errorButton)
        errorText
    }

    /**
     * Continue to checkout step 2
     */
    void continueToStep2() {
        clickElement(continueToCheckoutStep2Button)
    }

    void fillInfoAndContinueToStep2(String firstName, String lastName, String postalCode) {
        fillInFirstName(firstName)
        fillInLastName(lastName)
        fillInPostalCode(postalCode)
        continueToStep2()
    }

    /**
     * Verify if the user is on the checkout page
     */
    void isOnACheckoutPage() {
        waitForPageLoad()
        Assert.assertTrue("User is not on the checkout page", getCurrentUrl().contains("checkout-step-one"))
    }
}
