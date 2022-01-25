/*
 * Created By: Hiren Patel
 * Project Name: BestBuyAPI-Serenity-Week-21
 */

package com.bestbuyapi.bestbuyinfo;

import com.bestbuyapi.constants.EndPoints;
import com.bestbuyapi.model.ProductsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductsSteps {
    @Step("Getting all Products information ")
    public ValidatableResponse getAllProducts() {
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then().log().all();
    }

    @Step("Creating Product with name : {0}, type: {1}, price: {2}, shipping: {3}, upc: {4},description: {5}, manufacturer: {6},model: {7},url: {8},image: {9}")
    public ValidatableResponse createProduct(String name, String type, Double price, Integer shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        ProductsPojo productsPojo = ProductsPojo.getProductsPojo(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        return SerenityRest.given().log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(productsPojo)
                .post(EndPoints.POST_A_PRODUCT)
                .then().log().all();
    }

    @Step("Updating Product information with ProductID: {0}, name : {1}, type: {2}, price: {3}, shipping: {4}, upc: {5},description: {6}, manufacturer: {7},model: {8},url: {9},image: {10}")
    public ValidatableResponse updateProduct(int productId, String name, String type, Double price, Integer shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        ProductsPojo productsPojo = ProductsPojo.getProductsPojo(name, type, price, shipping, upc, description, manufacturer, model, url, image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", productId)
                .when()
                .body(productsPojo)
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Deleting Product information with ProductID: {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Getting Product information with ProductID: {0}")
    public ValidatableResponse getProductById(int productId) {
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all();
    }

}
