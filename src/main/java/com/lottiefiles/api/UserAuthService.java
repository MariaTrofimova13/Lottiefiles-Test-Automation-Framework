package com.lottiefiles.api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

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
        String body = "{\n" +
                "  \"query\": \"mutation passwordLogin($password: String!, $email: String!) { passwordLogin(web: true, password: $password, email: $email) { tokenType accessToken additionalAuthRequired expiresAt __typename } }\",\n" +
                "  \"variables\": {\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "  }\n" +
                "}";
        return body;
    }

    public void doRequest() {
        doRequest("test@gmail.com", "1122334");
    }

    public void doRequest(String email, String password) {
        response =
                given().baseUri("https://accounts.lottiefiles.com")
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

    ;

}
