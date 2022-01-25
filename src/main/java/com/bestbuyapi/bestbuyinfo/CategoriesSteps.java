/*
 * Created By: Hiren Patel
 * Project Name: BestBuyAPI-Serenity-Week-21
 */

package com.bestbuyapi.bestbuyinfo;

import com.bestbuyapi.constants.EndPoints;
import com.bestbuyapi.model.CategoriesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CategoriesSteps {
    @Step("Getting all Categories information ")
    public ValidatableResponse getAllCategories() {
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then().log().all();
    }

    @Step("Creating Category with name : {0}, id: {1}")
    public ValidatableResponse createCategory(String name, String id) {
        CategoriesPojo categoriesPojo = CategoriesPojo.getCategoriesPojo(name,id);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(categoriesPojo)
                .post(EndPoints.POST_A_CATEGORY)
                .then().log().all();
    }


    @Step("Updating Category information with name : {0}, CategoryID: {1}")
    public ValidatableResponse updateCategory(String name, String categoryId) {
        CategoriesPojo categoriesPojo = CategoriesPojo.getCategoriesPojo(name, categoryId);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", categoryId)
                .when()
                .body(categoriesPojo)
                .patch(EndPoints.UPDATE_CATEGORY_BY_ID )
                .then().log().all();
    }

    @Step("Deleting Category information with CategoryID: {0}")
    public ValidatableResponse deleteCategory(String categoryId) {
        return SerenityRest
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", categoryId)
                .when()
                .delete(EndPoints.DELETE_CATEGORY_BY_ID)
                .then().log().all();
    }

    @Step("Getting Category information with CategoryID: {0}")
    public ValidatableResponse getCategoryById(String categoryId) {
        return SerenityRest
                .given()
                .pathParam("id", categoryId)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then().log().all();
    }

}
