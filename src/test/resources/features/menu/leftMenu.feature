Feature: Menu functionality
  As a user of site invitro.ru
  I am able to click any option of menu

  Background:
    Given the user navigated to the 'radiology' page
    And the user open medical services header menu option

  @menu @ui
  Scenario: Click all menu options
    When the user click each menu option
    Then each option is clickable

