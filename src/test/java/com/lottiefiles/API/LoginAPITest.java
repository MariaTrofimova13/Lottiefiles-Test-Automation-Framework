package com.lottiefiles.API;

import com.lottiefiles.api.UserAuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginAPITest {
    @Test
    public void testLoginStatusCode() {
        UserAuthService userAuthService = new UserAuthService();
        userAuthService.doRequest();
        userAuthService.printResponse();

        Assertions.assertEquals(400, userAuthService.getStatusCode());
    }

    @Test
    public void testLogin2() {
        UserAuthService userAuthService = new UserAuthService();
        userAuthService.doRequest("aaaa@test.com", "");
        userAuthService.printResponse();

        Assertions.assertEquals(500, userAuthService.getStatusCode());
    }
}
