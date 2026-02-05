package com.lottiefiles.api;

import io.restassured.response.Response;

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


    public void doRequest() {
        response = given().queryParam("type", "free").when().get(URL_SEARCH + "/" + searchQuery);
    }

    public int getStatusCode() {
        return response.then().extract().statusCode();
    }

    public String getBody() {
        return response.then().extract().body().asPrettyString();
    }

    //создаем дальше те методы которые нужны для тестов
}
