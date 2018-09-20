Feature: add
    As a user
    I can add and view income and expense
    so that I know how much money I have

Background:
    Given a user with income 300 and expense 100

Scenario: Earn money
    When I earn money 100 from my mother
    Then my money book balance is 300

Scenario: Pay money amount less than balance
    When I pay 50 to buy book
    Then my money book balance is 150

Scenario: Pay money amount more than balance
    When I pay 5000 to buy smartphone
    Then my money book balance is 200