/*
 * Created By: Hiren Patel
 * Project Name: BestBuyAPI-Serenity-Week-21
 */

package com.bestbuyapi.bestbuyinfo;

import com.bestbuyapi.constants.EndPoints;
import com.bestbuyapi.model.ServicesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ServicesSteps {

    @Step("Getting all Service information ")
    public ValidatableResponse getAllService() {
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then().log().all();
    }

    @Step("Creating Service with name : {0}")
    public ValidatableResponse createService(String name) {
        ServicesPojo servicesPojo = ServicesPojo.getServicesPojo(name);
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(servicesPojo)
                .post(EndPoints.POST_A_SERVICE)
                .then().log().all();
    }
//    @Step("Getting single Service information with name: {0}")
//    public HashMap<String, Object> getServiceInfoByName(String name) {
//        String p1 = "data.findAll{it.name='";
//        String p2 = "'}.get(0)";
//
//        return SerenityRest.given().log().all()
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1 + name + p2);
//    }
    @Step("Updating Service information with name : {0}")
    public ValidatableResponse updateService(String name, int serviceId) {
        ServicesPojo servicesPojo = ServicesPojo.getServicesPojo(name);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("serviceID", serviceId)
                .when()
                .body(servicesPojo)
                .put(EndPoints.UPDATE_SERVICE_BY_ID)
                .then().log().all();
    }
    @Step("Deleting Service information with ServiceID: {0}")
    public ValidatableResponse deleteService(int serviceId) {
        return SerenityRest
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("serviceID", serviceId)
                .when()
                .delete(EndPoints.DELETE_SERVICE_BY_ID)
                .then().log().all();
    }

    @Step("Getting Service information with ServiceID: {0}")
    public ValidatableResponse getServiceById(int serviceId) {
        return SerenityRest
                .given()
                .pathParam("serviceID", serviceId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then();
    }


}
