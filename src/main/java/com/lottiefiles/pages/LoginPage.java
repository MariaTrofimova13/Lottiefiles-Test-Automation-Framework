package com.lottiefiles.pages;

import com.lottiefiles.driver.Driver;
import org.openqa.selenium.By;

import static com.lottiefiles.driver.Driver.createRandomEmail;
import static com.lottiefiles.driver.Driver.createRandomPassword;

public class LoginPage {
    private final String IFRAME = "auth-client-iframe";

    public final String LOGIN_TITLE_TEXT = "Log in";
    public final String INVALID_LOGIN_ERROR_TEXT = "Incorrect email or password. Please enter them again.";
    public final String MESSAGE_AFTER_SUCCESSFUL_LOGIN_TEXT = "Join your team's workspace or create a new one";
    private final String REAL_EMAIL = "maria.tarasowa@tut.by";
    private final String REAL_PASSWORD = "Test132435test";

    private final String BUTTON_LOGIN_WITH_EMAIL = "//button[@id=\"button-password-login\"]/div[@class=\"flex justify-center items-center w-icon-lg h-icon-lg *:w-icon-lg *:h-icon-lg\"]";
    private final String LOGIN_TITLE = "//h2[@class=\"font-bold text-2xl\"]";
    private final String EMAIL_FIELD = "//div[@class=\"flex flex-col gap-4\"]/div[1]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]";
    private final String EMAIL_INPUT = "//div[@class=\"flex flex-col gap-4\"]//input[@placeholder=\"Email\"]";
    private final String PASSWORD_FIELD = "//div[@class=\"flex flex-col gap-4\"]/div[2]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]";
    private final String PASSWORD_INPUT = "//div[@class=\"flex flex-col gap-4\"]//input[@placeholder=\"Password\"]";
    private final String VISIBLE_BUTTON_LOGIN = "//button[@id=\"button-login-submit\"]";
    private final String INVALID_LOGIN_ERROR = "//p[@class=\"text-sm\"]";
    private final String MESSAGE_AFTER_SUCCESSFUL_LOGIN = "//h1[@class=\"w-full text-lg font-bold leading-8 text-gray-900 mb-1 text-center\"]";


    public String getLoginTitle() {
        return Driver.switchToFrameAndWaitAndGetText(IFRAME, LOGIN_TITLE);
    }

    public void clickLoginButtonWithEmail() {
        Driver.switchToFrameAndWaitAndClick(IFRAME, BUTTON_LOGIN_WITH_EMAIL);
    }

    public void clickEmailField() {
        Driver.waitAndClick(EMAIL_FIELD);
    }

    public void clickPasswordField() {
        Driver.waitAndClick(PASSWORD_FIELD);
    }

    public void inputRandomEmail(String email) {
        Driver.waitAndInput(EMAIL_INPUT, email);
    }

    public void inputRandomPassword(String password) {
        Driver.waitAndInput(PASSWORD_INPUT, password);
    }

    public void clickVisibleButtonLogin() {
        Driver.waitAndClick(VISIBLE_BUTTON_LOGIN);
    }

    public void fillLoginFormWithRealEmailAndPassword() {
        clickEmailField();
        inputRandomEmail(REAL_EMAIL);
        clickPasswordField();
        inputRandomPassword(REAL_PASSWORD);
        clickVisibleButtonLogin();
        Driver.switchToDefaultContent();
    }

    public String getMessageAfterLogin() {
        return Driver.waitAndGetText(MESSAGE_AFTER_SUCCESSFUL_LOGIN);
    }

    public void fillLoginFormWithRandomEmailAndPassword() {
        clickEmailField();
        inputRandomEmail(createRandomEmail());
        clickPasswordField();
        inputRandomPassword(createRandomPassword());
        clickVisibleButtonLogin();
    }

    public String getLoginErrorMessage() {
        return Driver.waitAndGetText(INVALID_LOGIN_ERROR);
    }

    public boolean isLoginButtonDisabledAfterEmailInput() {
        clickEmailField();
        inputRandomEmail(createRandomEmail());
        return Driver.getDriver().findElement(By.xpath(VISIBLE_BUTTON_LOGIN)).isDisplayed();
    }

    public boolean isLoginButtonDisabledAfterPasswordInput() {
        clickPasswordField();
        inputRandomPassword(createRandomPassword());
        return Driver.getDriver().findElement(By.xpath(VISIBLE_BUTTON_LOGIN)).isDisplayed();
    }


}
