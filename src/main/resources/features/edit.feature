Feature: edit
    As a user
    I can edit income and expense (amount, date, category, description)

Background:
    Given a user with income 100 and expense 20

Scenario: Edit earn money
    When I edit income in index 0 to 50
    Then my money book balance is 30

Scenario: Edit pay money
    When I edit expense in index 0 to 40
    Then my money book balance is 60




