package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import utils.PageObjectManager
import utils.PropertyReader

class StepDefinitions extends BaseStepDefinitions {
  static PropertyReader propertyReader

  @Given("The user navigated to the application login page")
  void launchUrl() {
    propertyReader = new PropertyReader()
    launchURL(propertyReader.getPropertyByName("url"))
  }

  @When("The user navigated to the cart page")
  void goToCart() {
    PageObjectManager.getMiniCartPage()
    PageObjectManager.miniCartPage.gotToCartPage()
  }

  @When("The user navigated to the checkout page")
  void goToCheckout() {
    PageObjectManager.getCartPage()
    PageObjectManager.cartPage.continueToCheckoutPage()
  }

  @When("The user navigated to the checkout complete page")
  void goToCheckoutComplete() {
    PageObjectManager.getCheckoutOverview()
    PageObjectManager.checkoutOverview.continueToCheckoutFinish()
  }

  @When("The user enters {string}, {string} and {string} and continues to next step")
  void the_user_enters_details_and_continues(String firstName, String lastName, String postalCode) {
    PageObjectManager.getCheckoutPage()
    PageObjectManager.checkoutPage.fillInfoAndContinueToStep2(firstName, lastName, postalCode)
  }

  @When("The login page is displayed the user enters his credentials and presses the login button")
  static void userFillsInLogInCredentials() {
    propertyReader = new PropertyReader()
    String userName = propertyReader.getPropertyByName("standardUser")
    String password = propertyReader.getPropertyByName("password")
    PageObjectManager.getLogInPage()
    PageObjectManager.loginPage.logIn(userName, password)
  }

  @When("The login page is displayed the user enters incorrect credentials and presses the login button")
  static void userFillsInInvalidLogInCredentials() {
    PageObjectManager.getLogInPage()
    PageObjectManager.loginPage.logIn("wrongUser", "password")
  }

  @When('^The user adds a product "([^"]*)" from the all products page to his cart$')
  static void the_user_adds_a_product_from_the_all_products_page_to_his_cart(String productName) {
    PageObjectManager.getAllProductsPage()
    PageObjectManager.allProductsPage.addToCart(productName)
  }

  @When('^The user adds a product from the product details page to his cart$')
  static void the_user_adds_a_product_from_the_products_details_page_to_his_cart() {
    PageObjectManager.getProductDetailsPage()
    PageObjectManager.productDetailsPage.addToCart()
  }

  @When('^The user selects a product "([^"]*)" from the all products page$')
  static void the_user_selects_a_product_from_the_all_products_page(String productName) {
    PageObjectManager.getAllProductsPage()
    PageObjectManager.allProductsPage.selectProductFromTitle(productName)
  }


  @Then('^The addition of the product is reflected in the cart product count balloon as "([^"]*)"$')
  static void the_addition_of_the_product_is_the_reflected_in_the_cart_products_count_balloon_as(String expectedCount) {
    PageObjectManager.getMiniCartPage()
    PageObjectManager.miniCartPage.getProductCountInCart(expectedCount)
  }

  @Then("Validate that the user is on the main page of products")
  static void verifySuccessfulLogin() {
    PageObjectManager.getAllProductsPage()
    PageObjectManager.allProductsPage.isOnAllProductsPage()
  }

  @Then('^The user should see a meaningful warning message \"([^\"]*)\"')
  static void verifyInvalidCredentialsErrorMessage(String errorMessage) {
    PageObjectManager.getLogInPage()
    PageObjectManager.logInPage.verifyInvalidCredentialsErrorMessage(errorMessage)
  }

  @Then("Validate that the user is on the product details page")
  static void verify_the_user_is_on_the_product_details_page() {
    PageObjectManager.getProductDetailsPage()
    PageObjectManager.productDetailsPage.isOnProductDetailsPage()
  }

  @Then("Validate that the user is on the cart page")
  static void verify_the_user_is_on_the_cart_page() {
    PageObjectManager.getCartPage()
    PageObjectManager.cartPage.isOnACartPage()
  }

  @Then("Validate that the user is on the checkout page")
  static void verify_the_user_is_on_the_checkout_page() {
    PageObjectManager.getCheckoutPage()
    PageObjectManager.checkoutPage.isOnACheckoutPage()
  }

  @Then('^The product "([^"]*)" is on the cart page$')
  static void the_product_is_on_the_cart_page(String productName) {
    PageObjectManager.getCartPage()
    PageObjectManager.cartPage.verifyProductIsAddedToCart(productName)
  }

  @Then('^The product "([^"]*)" is on the checkout overview page$')
  static void the_product_is_on_the_checkout_overview_page(String productName) {
    PageObjectManager.getCheckoutOverviewPage()
    PageObjectManager.checkoutOverview.verifyProductIsAtCheckoutOverview(productName)
  }

  @Then("Logout from the application")
  static void signOut() {
    PageObjectManager.getLogInPage()
    PageObjectManager.loginPage.logOut()
  }
}
