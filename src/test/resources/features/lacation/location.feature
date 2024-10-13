Feature: Location functionality
  As a user of site invitro.ru
  I am able to see and change city of my location

  Background:
    Given the user navigated to the 'home' page

  @location @ui
  Scenario: City can be changed by user
    When the user change city to 'Омск'
    Then current city is 'Омск'