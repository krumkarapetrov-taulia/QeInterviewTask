package utils

import pages.AllProductsPage
import pages.CartPage
import pages.CheckoutOverview
import pages.CheckoutPage
import pages.LoginPage
import pages.MiniCartPage
import pages.ProductDetailsPage

class PageObjectManager {

  static LoginPage loginPage
  static AllProductsPage allProductsPage
  static ProductDetailsPage productDetailsPage
  static MiniCartPage miniCartPage
  static CartPage cartPage
  static CheckoutPage checkoutPage
  static CheckoutOverview checkoutOverview
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

  static ProductDetailsPage getProductDetailsPage() {
    return productDetailsPage = new ProductDetailsPage(DriverManager.getDriver())
  }

  static MiniCartPage getMiniCartPage() {
    return miniCartPage = new MiniCartPage(DriverManager.getDriver())
  }

  static CartPage getCartPage() {
    return cartPage = new CartPage(DriverManager.getDriver())
  }

  static CheckoutPage getCheckoutPage() {
    return checkoutPage = new CheckoutPage(DriverManager.getDriver())
  }

  static CheckoutOverview getCheckoutOverviewPage() {
    return checkoutOverview = new CheckoutOverview(DriverManager.getDriver())
  }
}
