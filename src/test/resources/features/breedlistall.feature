Feature: Dog API - /breeds/list/all endpoint

  @BREEDLISTALL
  Scenario: Validate GET /breeds/list/all returns all breeds from dataset
    When The user sends a GET request to /breeds/list/all
    Then the status code should be 200
    And the 'status' field should be 'success'
    And the 'message' field should contain all breeds from the dataset
