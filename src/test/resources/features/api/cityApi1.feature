Feature: Location api functionality
  As a tester of site invitro.ru
  I have to check some response

  @city @api @parametrized
  Scenario: Send request to current-city and check resp
    Then i send req and check resp
      | code     | city     | guid                                 |
      | moscow   | Москва   | f1c3c4f0-3426-4cda-8449-e5d326e02f97 |
      | taganrog | Таганрог | 0d198a8f-dbe8-4f1b-839f-9a8a49bd3e66 |
      | derbent  | Дербент  | c06f5863-f1b8-4eeb-bbd4-10eb72bc4f24 |
