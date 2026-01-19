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
    public void openHomePageAndCloseCookies(){
        homePage = new HomePage();
        homePage.open();
        homePage.closeCookies();
    }

    @Test
    public void testLoginHasTitle() {
        homePage.clickLoginButton();
        Assertions.assertEquals(homePage.LOGIN_TITLE_TEXT, homePage.getLoginTitle());
    }

    @Test
    public void testOpenLoginWithEmail() {
        homePage.clickLoginButton();
        homePage.clickLoginButtonWithEmail();

    }

    @AfterEach
    public void closeBrowser() {
        Driver.quit();
    }

}
