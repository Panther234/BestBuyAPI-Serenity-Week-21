@STORES

Feature: Testing the Stores features on the best buy application

  Scenario: Verify if best buy stores application can be accessed by users
    When User sends a GET request to the stores endpoint,they must get back a valid status code 200

  Scenario: Create a new Store & verify if the product is added
    When I create a new Store by providing the information name, type,  address, address2, city, state, zip, lat, lng, hours
    Then I verify that Store is created with name

  Scenario:Getting the Store by id and verifying
    When I get the Store created with ID
    Then I verify that the Store with ID is obtained

  Scenario:Updating the Store created and verify it is updated with status 200
    When I update the store with name type
    Then I verify that the information is updated in the store

  Scenario:Deleting the Store created and verify it is deleted
    When I delete the Store created with ID
    Then I verify that the Store is deleted and get a status 404
