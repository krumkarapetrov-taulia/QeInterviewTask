Feature: Verify that the login functionality works as per requirements

  @Login
  Scenario: As a user I want to be able to login when using correct credentials
    Given The user navigated to the application login page
    When The login page is displayed the user enters his credentials and presses the login button
    Then Validate that the user is on the main page of products
    And Logout from the application

  @Login
  Scenario: As a user I should not be allowed to login with incorrect credentials and see a meaningful error message
    Given The user navigated to the application login page
    When The login page is displayed the user enters incorrect credentials and presses the login button
    Then The user should see a meaningful warning message "Epic sadface: Username and password do not match any user in this service"

