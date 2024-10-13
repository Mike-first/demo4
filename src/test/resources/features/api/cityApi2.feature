Feature: Check API response for different cities

  @city @api
  Scenario Outline: Validate city response
    Given I make a GET request to the API with CODE "<code>"
    Then I should receive a response code of <statusCode>
    And the response body should contain "<expectedCity>"

    Examples:
      | code    | statusCode | expectedCity |
      | bajmak  | 200        | Bajmak       |
      | moscow  | 200        | Moscow       |
      | london  | 200        | London       |
