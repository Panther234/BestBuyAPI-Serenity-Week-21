package com.bestbuyapi.cucumber.steps;

/*
 * Created By: Hiren Patel
 * Project Name: BestBuyAPI-Serenity-Week-21
 */

import com.bestbuyapi.bestbuyinfo.ServicesSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class ServicesStepDefs {

    static String name = "Electronics" + TestUtils.getRandomName();
    static Integer serviceId;
    static ValidatableResponse response;

    @Steps
    ServicesSteps servicesSteps;

    @When("^User sends a GET request to the services endpoint$")
    public void userSendsAGETRequestToTheServicesEndpoint() {
        response = servicesSteps.getAllService();
    }

    @Then("^User must get back a valid status code(\\d+)$")
    public void userMustGetBackAValidStatusCode(int statusCode) {
        response.statusCode(200);
    }

    @When("^I create a new service by providing the name \"([^\"]*)\"$")
    public void iCreateANewServiceByProvidingTheName(String name) {
        ValidatableResponse response = servicesSteps.createService(name);
        response.log().all().statusCode(201);
        serviceId = response.extract().path("id");
        System.out.println("service Id is: " + serviceId);
    }

    @Then("^I verify that the service with name \"([^\"]*)\" and \"([^\"]*)\" is created$")
    public void iVerifyThatTheServiceWithIsCreated(String name, String id) {
        ValidatableResponse response = servicesSteps.getServiceById(serviceId);
        response.statusCode(200);
        assertThat(response.extract().body().jsonPath().get("name"), Matchers.equalTo(name));
        assertThat(response.extract().body().jsonPath().getInt("id"), equalTo(serviceId));
    }


    @When("^I update the service with name$")
    public void iUpdateTheServiceWithName() {
        name = name + " (Updated)";
        ValidatableResponse response = servicesSteps.updateService(name,serviceId);
        response.statusCode(200);
    }

    @Then("^I verify that the information is updated in the services$")
    public void iVerifyThatTheInformationIsUpdatedInTheServices() {
        ValidatableResponse response = servicesSteps.getServiceById(serviceId);
        assertThat(response.extract().body().jsonPath().get("name"), CoreMatchers.equalTo(name));
    }

    @When("^I delete the service created with \"([^\"]*)\"$")
    public void iDeleteTheServiceCreatedWith(String id) {
        servicesSteps.deleteService(serviceId).statusCode(200);
    }

    @Then("^I verify that the service is deleted and get a status is (\\d+)$")
    public void iVerifyThatTheServiceIsDeletedAndGetAStatusIs(int statusCode) {
        servicesSteps.getServiceById(serviceId).statusCode(404);
    }
}
