package com.bestbuyapi.cucumber.steps;

/*
 * Created By: Hiren Patel
 * Project Name: BestBuyAPI-Serenity-Week-21
 */

import com.bestbuyapi.bestbuyinfo.StoresSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;



public class StoresStepsDef {

    static String name = "Pinner" + TestUtils.getRandomValue();
    static String type = "SuperStore" + TestUtils.getRandomValue();
    static String address = TestUtils.getRandomValue() + ", Pinner road";
    static String address2 = "Harrow";
    static String city = "London";
    static String state = "London";
    static String zip = "123456";
    static Double lat = 45.126179;
    static Double lng = -93.261429;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;
    static ValidatableResponse response;

    @Steps
    StoresSteps storesSteps;

    @When("^User sends a GET request to the stores endpoint,they must get back a valid status code (\\d+)$")
    public void userSendsAGETRequestToTheStoresEndpointTheyMustGetBackAValidStatusCode(int code) {
        ValidatableResponse response = storesSteps.getAllStoresInfo();
        response.statusCode(200).log().all();
    }


    @When("^I create a new Store by providing the information name, type,  address, address(\\d+), city, state, zip, lat, lng, hours$")
    public void iCreateANewStoreByProvidingTheInformationNameTypeAddressAddressCityStateZipLatLngHours(int code) {
        ValidatableResponse response = storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.statusCode(201).log().all();
        storeId = response.extract().path("id");
        System.out.println("Id of store is: " + storeId);
    }

    @Then("^I verify that Store is created with name$")
    public void iVerifyThatStoreIsCreatedWithName() {
        ValidatableResponse response = storesSteps.getStoreById(storeId);
        response.statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(name));
    }


    @When("^I get the Store created with ID$")
    public void iGetTheStoreCreatedWithID() {
        storesSteps.getStoreById(storeId).statusCode(200).log().all();
    }

    @Then("^I verify that the Store with ID is obtained$")
    public void iVerifyThatTheStoreWithIDIsObtained() {
        ValidatableResponse response = storesSteps.getStoreById(storeId).statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().getInt("id"), equalTo(storeId));
    }

    @When("^I update the store with name type$")
    public void iUpdateTheStoreWithNameType() {
        name = name + " (Updated)";
        address = address + " (Updated)";
        ValidatableResponse response = storesSteps.updateStore(storeId, name, type, address, address2, city, state, zip, lat, lng, hours);
        response.statusCode(200);
    }

    @Then("^I verify that the information is updated in the store$")
    public void iVerifyThatTheInformationIsUpdatedInTheStore() {
        ValidatableResponse response =  storesSteps.getStoreById(storeId).statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(name));
        assertThat(response.extract().body().jsonPath().get("address"), equalTo(address));
    }

    @When("^I delete the Store created with ID$")
    public void iDeleteTheStoreCreatedWithID() {
        storesSteps.deleteStore(storeId).statusCode(200);
    }

    @Then("^I verify that the Store is deleted and get a status (\\d+)$")
    public void iVerifyThatTheStoreIsDeletedAndGetAStatus(int code) {
        storesSteps.getStoreById(storeId).statusCode(404);
    }
}
