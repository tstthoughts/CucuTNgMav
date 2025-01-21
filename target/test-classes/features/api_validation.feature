Feature: API Validation
#https://ultimateqa.com/dummy-automation-websites/
  Scenario: Validate GET API response
    When user sends a GET request to "/api/users?page=2"
    Then response status code should be 200
    And response should contain "page" with value "2"

  Scenario: Validate POST API response
    When user sends a POST request to "/api/users" with payload
      | "name"  | "John Doe"  |
      | "job" | "leader" |
    Then response status code should be 201
    And response should contain "id" with value "5"
