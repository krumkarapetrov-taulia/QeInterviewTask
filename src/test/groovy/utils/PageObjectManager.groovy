package utils

import pages.AllProductsPage
import pages.LoginPage

class PageObjectManager {

  static LoginPage loginPage
  static AllProductsPage allProductsPage
  private static PageObjectManager pageObjectManager

  static {
    try {
      pageObjectManager = new PageObjectManager()
    } catch (IOException e) {
      throw new RuntimeException(e)
    }
  }

  private PageObjectManager() throws IOException {

  }

  static LoginPage getLogInPage() {
    return loginPage = new LoginPage(DriverManager.getDriver())
  }

  static AllProductsPage getAllProductsPage() {
    return allProductsPage = new AllProductsPage(DriverManager.getDriver())
  }

}
