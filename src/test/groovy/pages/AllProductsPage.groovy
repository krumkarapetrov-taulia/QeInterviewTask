package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class AllProductsPage {

  WebDriver driver

  AllProductsPage(WebDriver driver) {
    this.driver = driver
    PageFactory.initElements(driver, this)
  }
}
