package com.bestbuyapi.cucumber.steps;
/*
 * Created By: Hiren Patel
 * Project Name: BestBuyAPI-Serenity-Week-21
 */

import com.bestbuyapi.bestbuyinfo.CategoriesSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class CategoriesStepDefs {

    static String name = "Electronics" + TestUtils.getRandomName();
    static String id = TestUtils.getRandomName();
    static String categoryId;
    static ValidatableResponse response;

    @Steps
    CategoriesSteps categoriesSteps;

    @When("^User sends a GET request to the categories endpoint$")
    public void userSendsAGETRequestToTheCategoriesEndpoint() {
        response = categoriesSteps.getAllCategories();
    }

    @Then("^User must get back a valid status code is (\\d+)$")
    public void userMustGetBackAValidStatusCode(int statusCode) {
        response.statusCode(200);
    }

    @When("^I create a new category by providing the information name  and id$")
    public void iCreateANewCategoryByProvidingTheInformationNameAndId() {
        ValidatableResponse response = categoriesSteps.createCategory(name,id);
        response.log().all().statusCode(201);
        categoryId = response.extract().path("id");
        System.out.println("category Id is: " + categoryId);
    }

    @Then("^I verify that the Category with name and id is created$")
    public void iVerifyThatTheCategoryWithNameAndIdIsCreated() {
        ValidatableResponse response = categoriesSteps.getCategoryById(categoryId);
        response.statusCode(200).log().all();
        categoryId = response.extract().body().path("id");
        assertThat(response.extract().body().path("id"), equalTo(categoryId));
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(name));
    }

    @When("^I update the Category with name$")
    public void iUpdateTheCategoryWithName() {
        name = name + " (Updated)";
        categoriesSteps.updateCategory(name, categoryId).statusCode(200).log().all();
    }

    @Then("^I verify that the information is updated in the Category$")
    public void iVerifyThatTheInformationIsUpdatedInTheCategory() {
        ValidatableResponse response = categoriesSteps.getCategoryById(categoryId);
        response.statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(name));
    }

    @When("^I delete the Category created with id$")
    public void iDeleteTheCategoryCreatedWithId() {
        categoriesSteps.deleteCategory(categoryId).statusCode(200).log().all();
    }

    @Then("^I verify that the Category is deleted and get the status (\\d+)$")
    public void iVerifyThatTheCategoryIsDeletedAndGetTheStatus(int statusCode) {
        categoriesSteps.getCategoryById(categoryId).statusCode(404).log().all();
    }


}
