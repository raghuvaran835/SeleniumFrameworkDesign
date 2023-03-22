
@tag
Feature: Login Error Validation
  I want to use this template for my feature file
  
  @ErrorValidation
  Scenario Outline: Validate error message should be displayed when we login using incorrect credentials
    Given I landed on Ecommerce Page
    When Logged in with Username <username> and Pasword <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      Examples: 
      | username  				| password   |
      | ratest1@gmail.com | Test@5678  |
