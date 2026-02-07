package com.lottiefiles.API;

import com.lottiefiles.api.UserAuthService;
import com.lottiefiles.dataGenerator.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginAPITest {
    UserAuthService userAuthService;

    @BeforeEach
    public void start(){
        userAuthService = new UserAuthService();
    }

    @Test
    public void invalidLoginTest() {
        userAuthService.doRequest();

        assertAll(
                () -> Assertions.assertEquals(400, userAuthService.getStatusCode()),
                () -> Assertions.assertEquals("Incorrect email or password. Please enter them again.", userAuthService.getErrorExtensionsMessageText()));
    }

    @Test
    public void emptyPasswordTest() {
        userAuthService.doRequest(DataGenerator.createRandomEmail(), "");

        assertAll(
                () ->Assertions.assertEquals(500, userAuthService.getStatusCode()),
                () -> Assertions.assertEquals("uiErrors?.some is not a function", userAuthService.getErrorMessage()));
    }

    @Test
    public void emptyEmailTest() {
        userAuthService.doRequest("", DataGenerator.createRandomPassword());

        assertAll(
                () ->Assertions.assertEquals(500, userAuthService.getStatusCode()),
                () -> Assertions.assertEquals("uiErrors?.some is not a function", userAuthService.getErrorMessage()));
    }

    @Test
    public void validLoginText(){
        userAuthService.doRequest("maria.tarasowa@tut.by", "Test132435test");
        userAuthService.printResponse();

        assertAll(
                () -> Assertions.assertEquals(200, userAuthService.getStatusCode())
        );
    }
}
