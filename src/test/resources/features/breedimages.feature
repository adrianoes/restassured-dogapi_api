Feature: Dog API - /breed/{breed}/images endpoint
  
  @BREEDIMAGES
  Scenario: Array of all the images from a breed
    When The user sends a GET request to /breed/clumber/images
    Then the status code should be 200 for all images
    And the 'status' field should be 'success'  
    And the 'message' field should contain all images for the chosen breed

  @BREEDIMAGES @NEGATIVE
  Scenario: Array of all the images from aaaaaaa
    When The user sends a GET request to /breed/aaaaaaa/images
    Then the status code should be 404 
    And the 'status' field should be 'error'  
    And the 'message' field should be "Breed not found (main breed does not exist)"

  @BREEDIMAGES @NEGATIVE
  Scenario: Array of all the images from 1
    When The user sends a GET request to /breed/1/images
    Then the status code should be 404 
    And the 'status' field should be 'error'  
    And the 'message' field should be "Breed not found (main breed does not exist)"	
	
  @BREEDIMAGES
  Scenario: Random image from a breed collection
    When The user sends a GET request to /breed/clumber/images/random
    Then the status code should be 200 for random image
    And the 'status' field should be 'success'  
    And the 'message' field should contain a random image for the chosen breed

  @BREEDIMAGES @NEGATIVE
  Scenario: Random image from aaaaaaa collection
    When The user sends a GET request to /breed/aaaaaaa/images/random
    Then the status code should be 404 
    And the 'status' field should be 'error'  
    And the 'message' field should be "Breed not found (main breed does not exist)"

  @BREEDIMAGES @NEGATIVE
  Scenario: Random image from 1 collection
    When The user sends a GET request to /breed/1/images/random
    Then the status code should be 404 
    And the 'status' field should be 'error'  
    And the 'message' field should be "Breed not found (main breed does not exist)"

  @BREEDIMAGES
  Scenario: Multiple random image from a breed
    When The user sends a GET request to /breed/clumber/images/random/3
    Then the status code should be 200 for 3 random images
    And the 'status' field should be 'success'  
    And the 'message' field should contain 3 random images for the chosen breed
	
  @BREEDIMAGES @NEGATIVE
  Scenario: 51 multiple random image from a breed
    When The user sends a GET request to /breed/clumber/images/random/51
    Then the status code should be 200 for 51 random images
    And the 'status' field should be 'success'  
    And the 'message' field should contain 51 random images for the chosen breed
	
  @BREEDIMAGES @NEGATIVE
  Scenario: a multiple random image from a breed
    When The user sends a GET request to /breed/clumber/images/random/a
    Then the status code should be 400
    And the 'status' field should be 'error'  
    And the 'message' field should not contain 'https:\/\/images.dog.ceo\/breeds\/'