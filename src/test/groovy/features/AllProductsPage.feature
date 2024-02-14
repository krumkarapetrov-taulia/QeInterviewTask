Feature: Verify the functionality of the All Products page

  @AllProducts
  Scenario: As a user I want to add a product to my cart from the all products page
    Given The user navigated to the application login page
    When The login page is displayed the user enters his credentials and presses the login button
    And Validate that the user is on the main page of products
    And The user adds a product "Sauce Labs Backpack" from the all products page to his cart
    Then The addition of the product is reflected in the cart product count balloon as "1"
    And Logout from the application