@test
Feature: login functionality

Background:
  Given user should be on login page

  @valid
  Scenario: Invalid_login_TC01
    When user enter the invalid credentials
    And click on login button
    Then user should navigated to login page
    And use can see the error message

Scenario: Valid_login_TC02
When user enter the valid credentials
And click on login button
Then user should navigated to home page
And use can see the logout link




  @bhumi
  Scenario Outline: Invalid login with different data set
    When user enter the invalid credentials as username "<userid>" and password "<password>"
    And click on login button
    Then user should navigated to login page
    And use can see the error message
    Examples:
    |userid | password |
    |admin1 |pwd1      |
    |admin2 |pwd2      |
    |admin3 |pwd3      |

   @123
  Scenario: Invalid login
    When user enter the invalid credentials "<uid>" ,"<pwd>" and click on login button
    |uid | pwd |
    |ad1 | pd1 |
    |ad2 | pd1 |
    |ad3 | pd1 |
    Then user should navigated to login page
    And use can see the error message


