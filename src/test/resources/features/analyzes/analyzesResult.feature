Feature: Location functionality
  As a user of site invitro.ru
  I am able to see analyzes result

  Background:
    Given the user navigated to the 'analyzes' page
    And the user click on analyzes result button

  @analyzesResult @ui
  Scenario: User see title on analyzes result page
    Then title 'Введите индивидуальный номер заказа, чтобы посмотреть результаты анализов' is shown

  @analyzesResult @ui
  Scenario: Empty fields search produce an error on analyzes result page
    When the user search for results
    Then error message 'Поля Код ИНЗДата рожденияФамилия обязательны для заполнения' is shown
    And  fields are highlighted red

  @analyzesResult @ui
  Scenario: Filled fields search produce an error on analyzes result page
    When the user fill 'Код ИНЗ' field with '231231231'
    And the user fill 'Дата рождения' field with '11.12.2000'
    And the user fill 'Фамилия' field with 'тест'
    Then fields are filled with correct data
