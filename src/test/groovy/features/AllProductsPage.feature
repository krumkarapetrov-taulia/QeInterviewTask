Feature: Verify the functionality of the All Products page

  @AllProducts
  Scenario: As a customer I want to add a product to my cart from the all products page
    Given The user navigated to the application login page
    When The login page is displayed the user enters his credentials and presses the login button
    And Validate that the user is on the main page of products
    And The customer adds a product from the all products page to his cart
    Then The addition of the product is reflected in the cart product count balloon as "1"
    And Logout from the application