@CATEGORIES
Feature: Testing the Categories features on the best buy application

  Scenario: Verify if best buy application categories can be accessed by users
    When User sends a GET request to the categories endpoint
    Then User must get back a valid status code is 200

  Scenario:Create a new Category & verify if the Category is added
    When I create a new category by providing the information name  and id
    Then I verify that the Category with name and id is created

  Scenario:Updating the Category created and verify it is updated with status 200
    When I update the Category with name
    Then I verify that the information is updated in the Category

  Scenario:Deleting the Category created and verify it is deleted
    When I delete the Category created with id
    Then I verify that the Category is deleted and get the status 404
