package com.lottiefiles.api;

import com.lottiefiles.dataGenerator.*;
import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;

public class UserAuthService {
    private String urlUserGraphql = "https://accounts.lottiefiles.com/user/graphql";

    private Response response;

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
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
        return body;
    }

    public void doRequest() {
        doRequest(DataGenerator.createRandomEmail(), DataGenerator.createRandomPassword());
    }

    public void doRequest(String email, String password) {
        response =
                given()
                        .headers(getHeaders())
                        .body(getBody(email, password))
                        .when()
                        .post(urlUserGraphql);
    }

    public void printResponse() {
        response.then()
                .log().all();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getErrorMessage() {
        return response.jsonPath().getString("errors[0].message");
    }

    public String getErrorExtensionsMessageText(){
        return response.jsonPath().getString("errors[0].extensions.errors.ui[0].text");
    }

}
