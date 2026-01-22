package com.lottiefiles.UI;

import com.lottiefiles.driver.Driver;
import com.lottiefiles.pages.HomePage;
import com.lottiefiles.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    public void openHomePageAndCloseCookies() {
        homePage = new HomePage();
        homePage.open();
        homePage.closeCookies();
        homePage.clickLoginButton();

        loginPage = new LoginPage();
        loginPage.clickLoginButtonWithEmail();
    }

    @Test
    public void testOpenLoginWithEmail() {
        loginPage.clickLoginButtonWithEmail();
        Assertions.assertEquals(loginPage.LOGIN_TITLE_TEXT, loginPage.getLoginTitle());

    }

    @Test
    public void PositiveTestLogin() {
        loginPage.fillLoginFormWithRealEmailAndPassword();
        Assertions.assertEquals(loginPage.MESSAGE_AFTER_SUCCESSFUL_LOGIN_TEXT, loginPage.getMessageAfterLogin());
    }

    @Test
    public void testVerifyErrorOnRandomInvalidEmailAndPassword() {
        loginPage.fillLoginFormWithRandomEmailAndPassword();
        Assertions.assertEquals(loginPage.INVALID_LOGIN_ERROR_TEXT, loginPage.getLoginErrorMessage());
    }

    @Test
    public void testIsLoginButtonDisabledAfterEmailInput(){
        Assertions.assertTrue(loginPage.isLoginButtonDisabledAfterEmailInput(), "Кнопка 'Log In' должна быть недоступной после ввода email");
    }

    @Test
    public void testIsLoginButtonDisabledAfterPasswordInput(){
        Assertions.assertTrue(loginPage.isLoginButtonDisabledAfterPasswordInput(), "Кнопка 'Log In' должна быть недоступной после ввода пароля");
    }

    @AfterEach
    public void closeBrowser() {
        Driver.quit();
    }
}

