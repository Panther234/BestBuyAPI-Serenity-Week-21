/*
 * Created By: Hiren Patel
 * Project Name: BestBuyAPI-Serenity-Week-21
 */

package com.bestbuyapi.constants;

public class EndPoints {
    /**
     * This Endpoints for PRODUCTS
     */
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{id}";
    public static final String POST_A_PRODUCT = "/products";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{id}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{id}";

    /**
     * This Endpoints for STORES
     */
    public static final String GET_ALL_STORES = "/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{storeID}";
    public static final String POST_A_STORE = "/stores";
    public static final String UPDATE_STORE_BY_ID = "/stores/{storeID}";
    public static final String DELETE_STORE_BY_ID = "/stores/{storeID}";

    /**
     * This Endpoints for CATEGORIES
     */
    public static final String GET_ALL_CATEGORIES = "/categories";
    public static final String GET_SINGLE_CATEGORY_BY_ID = "/categories/{id}";
    public static final String POST_A_CATEGORY = "/categories";
    public static final String UPDATE_CATEGORY_BY_ID = "/categories/{id}";
    public static final String DELETE_CATEGORY_BY_ID = "/categories/{id}";

    /**
     * This Endpoints for SERVICES
     */
    public static final String GET_ALL_SERVICES = "/services";
    public static final String GET_SINGLE_SERVICE_BY_ID = "/services/{serviceID}";
    public static final String POST_A_SERVICE = "/services";
    public static final String UPDATE_SERVICE_BY_ID = "/services/{serviceID}";
    public static final String DELETE_SERVICE_BY_ID = "/services/{serviceID}";

}
