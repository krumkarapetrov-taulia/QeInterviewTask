Feature: Verify the functionality of the Checkout flow

  @Checkout
  Scenario Outline: As a user I want to add a product to my cart and go through the checkout process
    Given The user navigated to the application login page
    When The login page is displayed the user enters his credentials and presses the login button
    And Validate that the user is on the main page of products
    And The user selects a product "Sauce Labs Backpack" from the all products page
    And Validate that the user is on the product details page
    And The user adds a product from the product details page to his cart
    And The user navigated to the cart page
    Then The addition of the product is reflected in the cart product count balloon as "1"
    And Validate that the user is on the cart page
    And The product "Sauce Labs Backpack" is on the cart page
    And The user navigated to the checkout page
    And Validate that the user is on the checkout page
    And The user enters "<firstName>", "<lastName>" and "<postalCode>" and continues to next step
    And The product "Sauce Labs Backpack" is on the checkout overview page
    And The user navigated to the checkout complete page
    And Logout from the application
    Examples: Valid user information
      | firstName    | lastName | postalCode |
      | fName        |   lName  |   1000     |
