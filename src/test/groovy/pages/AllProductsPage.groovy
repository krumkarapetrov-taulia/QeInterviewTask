package pages

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import stepdefinitions.BaseStepDefinitions

class AllProductsPage extends BaseStepDefinitions {

  @FindBy(css = ".inventory_item button")
  List<WebElement> addToCartButtons

  WebDriver driver

  AllProductsPage(WebDriver driver) {
    this.driver = driver
    PageFactory.initElements(driver, this)
  }

  /**
   * Check if the user is on the main product page
   */
  void isOnAllProductsPage() {
    waitForPageLoad()
    Assert.assertTrue("User is not on the main products page", getCurrentUrl().contains("inventory"))
  }

  /**
   * Selects a product by its title/text
   * @param text the title of the product to select
   */
  void selectProductFromTitle(String text) {
    clickElement(getWebElementFromText(text))
  }

  /**
   * Add product to cart from its title/text
   * @param text The title of the product
   */
  void addToCart(String text) {
    String lastWord = getLastWordFromText(text).toLowerCase()
    addToCartButtons.stream().filter(el ->
            el.getAttribute("id").endsWith(lastWord)).forEach(WebElement::click)
  }
}
