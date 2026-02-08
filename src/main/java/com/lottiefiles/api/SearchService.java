package com.lottiefiles.api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SearchService {
    private final String URL_SEARCH = "https://lottiefiles.com/free-animations";
    private Response response;
    private String searchQuery;

    private static final Logger logger = LogManager.getLogger();

    public void setSearchQuery(String searchQuery) {
        logger.info("Установка поискового запроса: '{}'", searchQuery);
        this.searchQuery = searchQuery;
    }

    public String capitalFirstLetter(String searchQuery) {
        this.searchQuery = searchQuery;
        return searchQuery.substring(0, 1).toUpperCase() + searchQuery.substring(1);
    }

    private Map<String, String> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("type", "free");
        logger.info("Формирование параметров запроса");
        return queryParams;
    }

    public void doRequest() {
        logger.info("Выполнение GET-запроса");
        response = given().queryParams(getQueryParams()).when().get(URL_SEARCH + "/" + searchQuery);
    }

    public int getStatusCode() {
        int statusCode = response.then().extract().statusCode();
        logger.info("Получен статус-код ответа: {}", statusCode);
        return statusCode;
    }

    public String getBody() {
        String body = response.then().extract().body().asPrettyString();
        logger.info("Получено тело ответа");
        return body;
    }

    public String getExpectedText(String searchQuery) {
        String expected = String.format("Free %s Animations", capitalFirstLetter(searchQuery));
        logger.info("Ожидаемый текст по запросу '{}': '{}'", searchQuery, expected);
        return expected;
    }
}
