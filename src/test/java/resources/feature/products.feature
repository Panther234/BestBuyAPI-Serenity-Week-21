@PRODUCTS
Feature: Testing the Product features on the best buy application

  Scenario: Verify if best buy product application can be accessed by users
    When User sends a GET request to the product endpoint
    Then User must get back a valid status code 200

  Scenario Outline: Create a new Product & verify if the product is added
    When I create a new product by providing the information name "<NAME>" type "<TYPE>" upc "<UPC>" price "<PRICE>"description "<DESCRIPTION>" model "<MODEL>"
    Then I verify that product is created with name "<NAME>"

    Examples:
      | NAME            | TYPE   | UPC       | PRICE | DESCRIPTION          | MODEL      |
      | Apple iPhone 12 | IO0123 | 123456789 | 95.99 | iPhone 12 with 512GB | iPhone12UK |

  Scenario:Getting the product by id and verifying
    When I get the product created with "id"
    Then I verify that the product with "id" is obtained

  Scenario Outline:Updating the product created and verify it is updated with status 200
    When I update the product with name "<NAME>" type "<TYPE>" upc "<UPC>" price "<PRICE>"description "<DESCRIPTION>" model "<MODEL>"
    Then I verify that the information with name "<NAME>" is updated in the product
    Examples:
      | NAME            | TYPE   | UPC       | PRICE | DESCRIPTION          | MODEL      |
      | Apple iPhone 13 | IO0133 | 999999999 | 99.99 | iPhone 13 with 512GB | iPhone13UK |

  Scenario:Deleting the product created and verify it is deleted
    When I delete the product created with "id"
    Then I verify that the product is deleted and get a status is 404
