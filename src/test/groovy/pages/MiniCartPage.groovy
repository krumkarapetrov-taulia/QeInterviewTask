package pages

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import stepdefinitions.BaseStepDefinitions

class MiniCartPage extends BaseStepDefinitions {

    @FindBy(css = "#shopping_cart_container")
    WebElement miniCart

    @FindBy(css = "#shopping_cart_container span")
    WebElement miniCartItemCounter

    WebDriver driver

    MiniCartPage(WebDriver driver) {
        this.driver = driver
        PageFactory.initElements(driver, this)
    }

    /**
     * Check if after login the page header is present and if text is as expected
     */
    void getProductCountInCart(String expectedCount) {
        isElementPresent(miniCartItemCounter)
        String actualCount = getText(miniCartItemCounter);
        Assert.assertEquals("Cart product count should be " + expectedCount, expectedCount, actualCount);
    }

    void gotToCartPage() {
        miniCart.click()
    }
}

