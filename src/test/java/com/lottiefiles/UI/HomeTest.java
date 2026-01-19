package com.lottiefiles.UI;

import com.lottiefiles.pages.HomePage;
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
    public void testLogin() {
        homePage.clickLoginButton();
        homePage.clickLoginButtonWithEmail();
    }

}
