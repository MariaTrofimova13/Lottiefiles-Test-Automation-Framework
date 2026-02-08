package com.lottiefiles.pages;

import com.lottiefiles.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    private final String IFRAME = "auth-client-iframe";

    public final String LOGIN_TITLE_TEXT = "Log in";
    public final String INVALID_LOGIN_ERROR_TEXT = "Incorrect email or password. Please enter them again.";
    public final String MESSAGE_AFTER_SUCCESSFUL_LOGIN_TEXT = "Join your team's workspace or create a new one";
    public final String REAL_EMAIL = "maria.tarasowa@tut.by";
    public final String REAL_PASSWORD = "Test132435test";

    private final String BUTTON_LOGIN_WITH_EMAIL = "//button[@id=\"button-password-login\"]/div[@class=\"flex justify-center items-center w-icon-lg h-icon-lg *:w-icon-lg *:h-icon-lg\"]";
    private final String LOGIN_TITLE = "//h2[@class=\"font-bold text-2xl\"]";
    private final String EMAIL_FIELD = "//div[@class=\"flex flex-col gap-4\"]/div[1]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]";
    private final String EMAIL_INPUT = "//div[@class=\"flex flex-col gap-4\"]//input[@placeholder=\"Email\"]";
    private final String PASSWORD_FIELD = "//div[@class=\"flex flex-col gap-4\"]/div[2]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]";
    private final String PASSWORD_INPUT = "//div[@class=\"flex flex-col gap-4\"]//input[@placeholder=\"Password\"]";
    private final String VISIBLE_BUTTON_LOGIN = "//button[@id=\"button-login-submit\"]";
    private final String INVALID_LOGIN_ERROR = "//p[@class=\"text-sm\"]";
    private final String MESSAGE_AFTER_SUCCESSFUL_LOGIN = "//h1[@class=\"w-full text-lg font-bold leading-8 text-gray-900 mb-1 text-center\"]";

    private static final Logger logger = LogManager.getLogger();

    public String getLoginTitleText() {
        String loginTitleText = Driver.waitAndGetText(LOGIN_TITLE);
        logger.info("Получен заголовок при входе" + loginTitleText);
        return loginTitleText;
    }

    public LoginPage clickLoginButtonWithEmail() {
        Driver.switchToFrameAndWaitAndClick(IFRAME, BUTTON_LOGIN_WITH_EMAIL);
        logger.info("Клик по кнопке входа с иконкой конверта");
        return this;
    }

    public LoginPage clickEmailField() {
        logger.info("Клик по полю email");
        Driver.waitAndClick(EMAIL_FIELD);
        return this;
    }

    public LoginPage clickPasswordField() {
        logger.info("Клик по полю пароль");
        Driver.waitAndClick(PASSWORD_FIELD);
        return this;
    }

    public LoginPage inputRandomEmail(String email) {
        logger.info("Ввод email: {}", email);
        Driver.waitAndInput(EMAIL_INPUT, email);
        return this;
    }

    public LoginPage inputRandomPassword(String password) {
        logger.info("Ввод пароля");
        Driver.waitAndInput(PASSWORD_INPUT, password);
        return this;
    }

    public LoginPage clickVisibleButtonLogin() {
        logger.info("Клик по кнопке входа");
        Driver.waitAndClick(VISIBLE_BUTTON_LOGIN);
        return this;
    }

    public String getMessageAfterLogin() {
        String message = Driver.waitAndGetText(MESSAGE_AFTER_SUCCESSFUL_LOGIN);
        logger.info("Сообщение после входа: {}", message);
        return message;
    }

    public String getLoginErrorMessage() {
        String errorMessage = Driver.waitAndGetText(INVALID_LOGIN_ERROR);
        logger.info("Сообщение об ошибке входа: {}", errorMessage);
        return errorMessage;
    }

    public boolean isLoginButtonDisabled() {
        boolean isDisplayed = Driver.isElementDisplayed(VISIBLE_BUTTON_LOGIN);
        logger.info("Статус отображения кнопки входа: {}", isDisplayed ? "неактивна" : "доступна");
        return isDisplayed;
    }
}
