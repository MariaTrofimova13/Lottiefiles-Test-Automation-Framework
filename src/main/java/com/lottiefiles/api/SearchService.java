package com.lottiefiles.api;

import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;

public class SearchService {
    private final String URL_SEARCH = "https://lottiefiles.com/free-animations";
    private Response response;
    private String searchQuery;

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String capitalFirstLetter(String searchQuery) {
        this.searchQuery = searchQuery;
        return searchQuery.substring(0, 1).toUpperCase() + searchQuery.substring(1);
    }

    private Map<String, String> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("type", "free");
        return queryParams;
    }


    public void doRequest() {
        response = given().queryParams(getQueryParams()).when().get(URL_SEARCH + "/" + searchQuery);
    }

    public int getStatusCode() {
        return response.then().extract().statusCode();
    }

    public String getBody() {
        return response.then().extract().body().asPrettyString();
    }

    public String getExpectedText(String searchQuery){
        return String.format("Free %s Animations", capitalFirstLetter(searchQuery));
    }
}
