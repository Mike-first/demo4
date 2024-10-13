Feature: Cart functionality
  As a user of site invitro.ru
  I am able to add analyzes to cart

  Background:
    Given the user navigated to the 'analyzes' page

  @cart @ui
  Scenario: Price in cart is equal to price while adding
    When the user add any analysis to cart
    And the user open cart
    Then analysis price in cart equal to previously added analysis