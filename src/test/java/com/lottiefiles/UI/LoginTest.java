package com.lottiefiles.UI;

import com.lottiefiles.dataGenerator.DataGenerator;
import com.lottiefiles.driver.Driver;
import com.lottiefiles.pages.HomePage;
import com.lottiefiles.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LoginTest {
    private LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger();

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
        logger.info("Тест: проверка наличия правильного заголовка страницы входа");
        Assertions.assertEquals(loginPage.LOGIN_TITLE_TEXT, loginPage.getLoginTitleText());
    }

    @Test
    public void PositiveTestLogin() {
        logger.info("Тест: успешный вход с валидными данными");
        loginPage.clickEmailField()
                .inputRandomEmail(loginPage.REAL_EMAIL)
                .clickPasswordField().inputRandomPassword(loginPage.REAL_PASSWORD)
                .clickVisibleButtonLogin();
        Driver.switchToDefaultContent();
        Assertions.assertEquals(loginPage.MESSAGE_AFTER_SUCCESSFUL_LOGIN_TEXT, loginPage.getMessageAfterLogin());
    }

    @Test
    public void testVerifyErrorOnRandomInvalidEmailAndPassword() {
        logger.info("Тест: проверка ошибки при вводе случайных некорректных email и пароля");
        loginPage.clickEmailField()
                .inputRandomEmail(DataGenerator.createRandomEmail())
                .clickPasswordField()
                .inputRandomPassword(DataGenerator.createRandomPassword())
                .clickVisibleButtonLogin();
        Assertions.assertEquals(loginPage.INVALID_LOGIN_ERROR_TEXT, loginPage.getLoginErrorMessage());
    }

    @Test
    public void testIsLoginButtonDisabledAfterEmailInput() {
        logger.info("Тест: проверка, что кнопка входа недоступна после заполнения поля email");
        loginPage.clickEmailField()
                .inputRandomEmail(DataGenerator.createRandomEmail());
        Assertions.assertTrue(loginPage.isLoginButtonDisabled(), "Кнопка 'Log In' должна быть недоступной после ввода email");
    }

    @Test
    public void testIsLoginButtonDisabledAfterPasswordInput() {
        logger.info("Тест: проверка, что кнопка входа недоступна после заполнения поля password");
        loginPage.clickPasswordField()
                .inputRandomPassword(DataGenerator.createRandomPassword());
        Assertions.assertTrue(loginPage.isLoginButtonDisabled(), "Кнопка 'Log In' должна быть недоступной после ввода пароля");
    }

    @AfterEach
    public void closeBrowser() {
        Driver.quit();
    }
}

