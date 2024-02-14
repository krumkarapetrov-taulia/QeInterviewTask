package pages

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import stepdefinitions.BaseStepDefinitions

class CartPage extends BaseStepDefinitions {

    @FindBy(css = "#shopping_cart_container")
    WebElement miniCartContainer

    @FindBy(id = "checkout")
    WebElement continueToCheckoutButton

    @FindBy(css = ".inventory_item_name")
    List<WebElement> cartItems

    WebDriver driver

    CartPage(WebDriver driver) {
        this.driver = driver
        PageFactory.initElements(driver, this)
    }

    /**
     * Verify if the user is on the cart page
     */
    void isOnACartPage() {
        waitForPageLoad()
        Assert.assertTrue("User is not on the cart page", getCurrentUrl().contains("cart"))
    }

    /**
     * Verify if the expected product title/text is in the products list in the cart
     * @param text the title/text of the product
     */
    void verifyProductIsAddedToCart(String text) {
        Assert.assertTrue("Item is not on the cart",
                cartItems.stream().anyMatch(el -> el.getText().contains(text)))
    }

    void continueToCheckoutPage() {
        clickElement(continueToCheckoutButton)
    }
}
