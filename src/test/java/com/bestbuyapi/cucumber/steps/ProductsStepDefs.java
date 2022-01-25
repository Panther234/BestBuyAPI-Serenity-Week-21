package com.bestbuyapi.cucumber.steps;
/*
 * Created By: Hiren Patel
 * Project Name: BestBuyAPI-Serenity-Week-21
 */

import com.bestbuyapi.bestbuyinfo.ProductsSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProductsStepDefs {


    static Integer shipping = 10;
    static String manufacturer = "Apple";
    static String url = "https://www.bestbuy.com/site/apple-macbook-pro-13-display-with-touch-bar-intel-core-i5-16gb-memory-512gb-ssd-space-gray/6287726.p?skuId=6287726";
    static String image = "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6287/6287726_sd.jpg;maxHeight=640;maxWidth=550";
    static int productId;

    static ValidatableResponse response;

    @Steps
    ProductsSteps productsSteps;

    @When("^User sends a GET request to the product endpoint$")
    public void userSendsAGETRequestToTheProductEndpoint() {
        response = productsSteps.getAllProducts();
    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode(int statusCode) {
        response.statusCode(200);
    }

    @When("^I create a new product by providing the information name \"([^\"]*)\" type \"([^\"]*)\" upc \"([^\"]*)\" price \"([^\"]*)\"description \"([^\"]*)\" model \"([^\"]*)\"$")
    public void iCreateANewProductByProvidingTheInformationNameTypeUpcPriceDescriptionModel(String name, String type, String upc, Double price, String description, String model) {
        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.statusCode(201).log().all();
        productId = response.extract().path("id");
        System.out.println("product id is: " + productId);
    }

    @Then("^I verify that product is created with name \"([^\"]*)\"$")
    public void iVerifyThatProductIsCreatedWithName(String name) {
        ValidatableResponse response = productsSteps.getProductById(productId);
        response.statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(name));
        assertThat(response.extract().body().jsonPath().getInt("id"), equalTo(productId));
    }

    @When("^I get the product created with \"([^\"]*)\"$")
    public void iGetTheProductCreatedWith(String id) {
        productsSteps.getProductById(productId).statusCode(200).log().all();
    }

    @Then("^I verify that the product with \"([^\"]*)\" is obtained$")
    public void iVerifyThatTheProductWithIsObtained(String id) {
        ValidatableResponse response = productsSteps.getProductById(productId).statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().getInt("id"), equalTo(productId));

    }

    @When("^I update the product with name \"([^\"]*)\" type \"([^\"]*)\" upc \"([^\"]*)\" price \"([^\"]*)\"description \"([^\"]*)\" model \"([^\"]*)\"$")
    public void iUpdateTheProductWithNameTypeUpcPriceDescriptionModel(String name, String type, String upc, Double price, String description, String model) {
        ValidatableResponse response = productsSteps.updateProduct(productId, name, type, price, shipping, upc, description, manufacturer, model, url, image);
    }

    @Then("^I verify that the information with name \"([^\"]*)\" is updated in the product$")
    public void iVerifyThatTheInformationWithNameIsUpdatedInTheProduct(String name) {
        ValidatableResponse response = productsSteps.getProductById(productId).statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(name));
    }

    @When("^I delete the product created with \"([^\"]*)\"$")
    public void iDeleteTheProductCreatedWith(String id) {
        productsSteps.deleteProduct(productId).statusCode(200);
    }

    @Then("^I verify that the product is deleted and get a status is (\\d+)$")
    public void iVerifyThatTheProductIsDeletedAndGetAStatusIs(int statusCode) {
        productsSteps.getProductById(productId).statusCode(404);
    }


}
