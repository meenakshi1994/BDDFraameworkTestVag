Feature: Test cases for RCB Players

  @Test1
  Scenario: Test to validate if team has only 4 foreign players
    Given the data of RCB players
    Then validate if the team has only 4 foreign players

  @Test2
  Scenario: Test to validate if the team has atleast 1 wicket keeper
    Given the data of RCB players
    Then validate if the team has atleast 1 wicket keeper