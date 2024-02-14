package pages

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import stepdefinitions.BaseStepDefinitions

class CheckoutOverview extends BaseStepDefinitions {
    @FindBy(id = "finish")
    WebElement finishCheckoutButton

    @FindBy(css = ".cart_item_label .inventory_item_name")
    List<WebElement> productList

    WebDriver driver

    CheckoutOverview(WebDriver driver) {
        this.driver = driver
        PageFactory.initElements(driver, this)
    }

    /**
     * Verify if the expected product title/text is in the products list in the checkout
     * @param text the title/text of the product
     */
    void verifyProductIsAtCheckoutOverview(String text) {
        Assert.assertTrue("Item is not on the cart",
                productList.stream().anyMatch(el -> el.getText().contains(text)))
    }

    /**
     * Continue to checkout complete page
     */
    void continueToCheckoutFinish() {
        clickElement(finishCheckoutButton)
    }
}
