package com.lottiefiles.UI;

import com.lottiefiles.driver.Driver;
import com.lottiefiles.pages.HomePage;
import com.lottiefiles.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lottiefiles.driver.Driver.createRandomEmail;
import static com.lottiefiles.driver.Driver.createRandomPassword;

public class LoginTest {
    private LoginPage loginPage;

    @BeforeEach
    public void openHomePageAndCloseCookies() {
        loginPage = new HomePage()
                .open()
                .closeCookies()
                .clickLoginButton()
                .clickLoginButtonWithEmail();
    }

    @Test
    public void testHasLoginTitle() {
        Assertions.assertEquals(loginPage.LOGIN_TITLE_TEXT, loginPage.getLoginTitle());

    }

    @Test
    public void PositiveTestLogin() {
        loginPage.clickEmailField()
                .inputRandomEmail(loginPage.REAL_EMAIL)
                .clickPasswordField().inputRandomPassword(loginPage.REAL_PASSWORD)
                .clickVisibleButtonLogin();
        Driver.switchToDefaultContent();
        Assertions.assertEquals(loginPage.MESSAGE_AFTER_SUCCESSFUL_LOGIN_TEXT, loginPage.getMessageAfterLogin());
    }

    @Test
    public void testVerifyErrorOnRandomInvalidEmailAndPassword() {
        loginPage.clickEmailField()
                .inputRandomEmail(createRandomEmail())
                .clickPasswordField()
                .inputRandomPassword(createRandomPassword())
                .clickVisibleButtonLogin();
        Assertions.assertEquals(loginPage.INVALID_LOGIN_ERROR_TEXT, loginPage.getLoginErrorMessage());
    }

    @Test
    public void testIsLoginButtonDisabledAfterEmailInput() {
        loginPage.clickEmailField()
                .inputRandomEmail(createRandomEmail());
        Assertions.assertTrue(loginPage.isLoginButtonDisabled(), "Кнопка 'Log In' должна быть недоступной после ввода email");
    }

    @Test
    public void testIsLoginButtonDisabledAfterPasswordInput() {
        loginPage.clickPasswordField()
                .inputRandomPassword(createRandomPassword());
        Assertions.assertTrue(loginPage.isLoginButtonDisabled(), "Кнопка 'Log In' должна быть недоступной после ввода пароля");
    }

    @AfterEach
    public void closeBrowser() {
        Driver.quit();
    }
}

