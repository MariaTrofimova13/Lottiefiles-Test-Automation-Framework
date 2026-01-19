package com.lottiefiles.UI;

import com.lottiefiles.driver.Driver;
import com.lottiefiles.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HomeTest {
    private HomePage homePage;

    @BeforeEach
    public void openHomePageAndCloseCookies() {
        homePage = new HomePage();
        homePage.open();
        homePage.closeCookies();
    }

    @Test
    public void testOpenLoginWithEmail() {
        homePage.clickLoginButton();
        homePage.clickLoginButtonWithEmail();
        Assertions.assertEquals(homePage.LOGIN_TITLE_TEXT, homePage.getLoginTitle());

    }

    @Test
    public void PositiveTestLogin() {
        homePage.fillLoginFormWithRealEmailAndPassword();
        Assertions.assertEquals(homePage.MESSAGE_AFTER_SUCCESSFUL_LOGIN_TEXT, homePage.getMessageAfterLogin());
    }

    @Test
    public void testVerifyErrorOnRandomInvalidEmailAndPassword() {
        homePage.fillLoginFormWithRandomEmailAndPassword();
        Assertions.assertEquals(homePage.INVALID_LOGIN_ERROR_TEXT, homePage.getLoginErrorMessage());
    }


    @AfterEach
    public void closeBrowser() {
        Driver.quit();
    }

}
