#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

@tag
Feature: Purchase the Order from Ecommerce Website
  #I want to use this template for my feature file
  
	Background:
	Given I landed on Ecommerce Page
	
  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with Username <username> and Pasword <password>
    When I add the product <productname> to Cart
    And Checkout the <productname> and Submit the Order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation Page.

    Examples: 
      | username  				| password   | productname  |
      | ratest1@gmail.com | Test@1234  | ZARA COAT 3  |
      
      
      
  #@tag2
  #Scenario: Title of your scenario
    #Given I want to write a step with precondition
    #And some other precondition
    #When I complete action
    #And some other action
    #And yet another action
    #Then I validate the outcomes
    #And check more outcomes  
