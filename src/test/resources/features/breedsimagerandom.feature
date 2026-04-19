Feature: Dog API - /breeds/image/random endpoint

  @BREEDSIMAGERANDOM
  Scenario: Single random image from all dogs collection
    When the user sends a GET request to /breeds/image/random
    Then the status code should be 200 for single image
    And the 'status' field should be 'success' for single image
    And the 'message' field should be a valid image URL for the chosen breed

  @BREEDSIMAGERANDOM
  Scenario: 3 random images from all dogs collection
    When the user sends a GET request to /breeds/image/random/3
    Then the status code should be 200 for three images
    And the 'status' field should be 'success' for three images
    And the 'message' field should be 3 valid image URLs for the 3 chosen breeds

  @BREEDSIMAGERANDOM @NEGATIVE
  Scenario: 51 random images from all dogs collection
    When the user sends a GET request to /breeds/image/random/51
    Then the status code should be 200 for fifty images
    And the 'status' field should be 'success' for fifty images
    And the 'message' field should be 50 valid image URLs for the 50 chosen breeds

  @BREEDSIMAGERANDOM @NEGATIVE
  Scenario: -1 random images from all dogs collection
    When the user sends a GET request to /breeds/image/random/-1
    Then the status code should be 400
    And the 'status' field should be 'error'
    And the 'message' field should not contain 'https://images.dog.ceo/breeds/'

  @BREEDSIMAGERANDOM @NEGATIVE
  Scenario: 'a' random images from all dogs collection
    When the user sends a GET request to /breeds/image/random/a
    Then the status code should be 400
    And the 'status' field should be 'error'
    And the 'message' field should not contain 'https://images.dog.ceo/breeds/'

  @BREEDSIMAGERANDOM @NEGATIVE
  Scenario: 0 random images from all dogs collection
    When the user sends a GET request to /breeds/image/random/0
    Then the status code should be 400
    And the 'status' field should be 'error'
    And the 'message' field should not contain 'https://images.dog.ceo/breeds/'

  @BREEDSIMAGERANDOM @NEGATIVE
  Scenario: @ random images from all dogs collection
    When the user sends a GET request to /breeds/image/random/@
    Then the status code should be 400
    And the 'status' field should be 'error'
    And the 'message' field should not contain 'https://images.dog.ceo/breeds/'




