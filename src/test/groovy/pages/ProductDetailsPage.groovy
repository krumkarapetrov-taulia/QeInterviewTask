package pages

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import stepdefinitions.BaseStepDefinitions

class ProductDetailsPage extends BaseStepDefinitions {

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addToCartButton

    @FindBy(css = "[href='#'] .inventory_item_name")
    List<WebElement> productNames

    WebDriver driver

    ProductDetailsPage(WebDriver driver) {
        this.driver = driver
        PageFactory.initElements(driver, this)
    }

    /**
     * To login in the application by filling in user and password
     */
    void isOnProductDetailsPage() {
        waitForPageLoad()
        Assert.assertTrue("User is not on the product details page", getCurrentUrl().contains("inventory-item"))
    }


    /**
     * To login in the application by filling in user and password
     */
    void addToCart() {
        isElementPresent(addToCartButton)
        addToCartButton.click()
    }
}
