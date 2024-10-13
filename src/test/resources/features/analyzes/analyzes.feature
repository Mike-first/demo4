Feature: Location functionality
  As a user of site invitro.ru
  I am able to search analyzes

  Background:
    Given the user navigated to the 'analyzes' page

  @analyzes @ui
  Scenario: Search analyzes by code
    When the user search for analyzes by key-words '1515'
    Then only relevant analyzes are shown