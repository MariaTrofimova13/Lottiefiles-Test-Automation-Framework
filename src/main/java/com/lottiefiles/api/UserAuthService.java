package com.lottiefiles.api;

import com.lottiefiles.dataBase.DataBase;
import com.lottiefiles.dataGenerator.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserAuthService {
    private String urlUserGraphql = "https://accounts.lottiefiles.com/user/graphql";
    private Response response;
    private static final Logger logger = LogManager.getLogger();

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        logger.info("Настройка Headers запроса");
        return headers;
    }

    private String getBody(String email, String password) {
        String body = String.format(""" 
                {
                 "query": "mutation passwordLogin($password: String!, $email: String!) { passwordLogin(web: true, password: $password, email: $email) { tokenType accessToken additionalAuthRequired expiresAt __typename } }",
                  "variables": {
                                 "email": "%s",
                                 "password": "%s"
                               }
                } 
                """,
                email, password
        );
        logger.info("Формирование тела запроса с email: '{}' и паролем: '{}'", email, password);
        logger.debug("Тело запроса: {}", body);
        return body;
    }

    public void doRequest() {
        logger.info("Выполнение запроса аутентификации с случайным email и паролем");
        doRequest(DataGenerator.createRandomEmail(), DataGenerator.createRandomPassword());
    }

    public void doRequest(String email, String password) {
        logger.info("Отправка POST-запроса");
        response =
                given()
                        .headers(getHeaders())
                        .body(getBody(email, password))
                        .when()
                        .post(urlUserGraphql);
    }

    public void doRequestRealUser() {
        logger.info("Выполнение запроса аутентификации с валидными данными");
        doRequest(DataBase.getEmailUserFromDataBase(), DataBase.getPasswordUserFromDataBase());
    }

    public void printResponse() {
        logger.info("Вывод полного ответа:");
        response.then()
                .log().all();
    }

    public int getStatusCode() {
        int statusCode = response.getStatusCode();
        logger.info("Статус-код ответа: {}", statusCode);
        return statusCode;
    }

    public String getErrorMessage() {
        String errorMessage = response.jsonPath().getString("errors[0].message");
        logger.warn("Сообщение об ошибке: '{}'", errorMessage);
        return errorMessage;
    }

    public String getErrorExtensionsMessageText() {
        String errorExtensionsMsg = response.jsonPath().getString("errors[0].extensions.errors.ui[0].text");
        logger.warn("Сообщение об ошибке: '{}'", errorExtensionsMsg);
        return errorExtensionsMsg;
    }
}
