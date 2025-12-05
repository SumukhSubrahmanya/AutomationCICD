@tag
Feature: Error validation
I want to use this template for my feature file

	@ErrorValidation
	Scenario Outline:
	Given I landed on Ecommerce Page
	When Logged in with username <name> and password <password>
	Then "Incorrect email or password." message is displayed
	
	Examples:
	| name 					   | password   |
	| testinguser1212@gmail.com| Test123@123  |
	