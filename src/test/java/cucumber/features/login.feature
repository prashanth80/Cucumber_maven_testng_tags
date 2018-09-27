@SingIn
Feature: Login users

  Scenario Outline: Successful Login
  Given Home page is loaded
  And Sign with <username> and <password>
  Then Login is successful

  Examples:
  | username | password |
  | admin    | admin    |
  | user1    | password1|
  | user2    | password2|