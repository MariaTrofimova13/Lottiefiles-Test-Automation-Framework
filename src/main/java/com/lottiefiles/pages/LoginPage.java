package com.lottiefiles.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final By IFRAME = By.id("auth-client-iframe");

    public final String LOGIN_TITLE_TEXT = "Log in";
    public final String INVALID_LOGIN_ERROR_TEXT = "Incorrect email or password. Please enter them again.";
    public final String MESSAGE_AFTER_SUCCESSFUL_LOGIN_TEXT = "Join your team's workspace or create a new one";
    private final String REAL_EMAIL = "maria.tarasowa@tut.by";
    private final String REAL_PASSWORD = "Test132435test";

    private final By BUTTON_LOGIN_WITH_EMAIL = By.xpath("//button[@id=\"button-password-login\"]/div[@class=\"flex justify-center items-center w-icon-lg h-icon-lg *:w-icon-lg *:h-icon-lg\"]");
    private final By LOGIN_TITLE = By.xpath("//h2[@class=\"font-bold text-2xl\"]");
    private final By EMAIL_FIELD = By.xpath("//div[@class=\"flex flex-col gap-4\"]/div[1]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]");
    private final By EMAIL_INPUT = By.xpath("//div[@class=\"flex flex-col gap-4\"]//input[@placeholder=\"Email\"]");
    private final By PASSWORD_FIELD = By.xpath("//div[@class=\"flex flex-col gap-4\"]/div[2]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]");
    private final By PASSWORD_INPUT = By.xpath("//div[@class=\"flex flex-col gap-4\"]//input[@placeholder=\"Password\"]");
    private final By VISIBLE_BUTTON_LOGIN = By.xpath("//button[@id=\"button-login-submit\"]");
    private final By INVALID_LOGIN_ERROR = By.xpath("//p[@class=\"text-sm\"]");
    private final By MESSAGE_AFTER_SUCCESSFUL_LOGIN = By.xpath("//h1[@class=\"w-full text-lg font-bold leading-8 text-gray-900 mb-1 text-center\"]");
    public String getLoginTitle() {
        waitAndSwitchToFrame();
        waitForVisibleOfElement(LOGIN_TITLE);
        String titleText = driver.findElement(LOGIN_TITLE).getText();
        driver.switchTo().defaultContent();
        return titleText;
    }

    public void clickLoginButtonWithEmail() {
        waitAndSwitchToFrame();
        waitForVisibleOfElement(BUTTON_LOGIN_WITH_EMAIL).click();
        driver.switchTo().defaultContent();
    }

    public void clickEmailField() {
        waitAndSwitchToFrame();
        waitForVisibleOfElement(EMAIL_FIELD).click();
        driver.switchTo().defaultContent();
    }

    public void clickPasswordField() {
        waitAndSwitchToFrame();
        waitForVisibleOfElement(PASSWORD_FIELD).click();
        driver.switchTo().defaultContent();
    }

    public void sendRandomEmail(String email) {
        waitAndSwitchToFrame();
        WebElement emailInput = waitForVisibleOfElement(EMAIL_INPUT);
        emailInput.clear();
        emailInput.sendKeys(email);
        driver.switchTo().defaultContent();
    }

    public void sendRandomPassword(String password) {
        waitAndSwitchToFrame();
        WebElement emailInput = waitForVisibleOfElement(PASSWORD_INPUT);
        emailInput.clear();
        emailInput.sendKeys(password);
        driver.switchTo().defaultContent();
    }

    public void clickVisibleButtonLogin() {
        waitAndSwitchToFrame();
        waitForVisibleOfElement(VISIBLE_BUTTON_LOGIN).click();
        driver.switchTo().defaultContent();
    }

    public String createRandomEmail(){
        return faker.internet().emailAddress();
    }

    public String createRandomPassword(){
        return faker.internet().password(8, 16);
    }

    public void fillLoginFormWithRealEmailAndPassword() {
        clickLoginButton();
        clickLoginButtonWithEmail();
        clickEmailField();
        sendRandomEmail(REAL_EMAIL);
        clickPasswordField();
        sendRandomPassword(REAL_PASSWORD);
        clickVisibleButtonLogin();
    }

    public String getMessageAfterLogin() {
        waitForVisibleOfElement(MESSAGE_AFTER_SUCCESSFUL_LOGIN);
        return driver.findElement(MESSAGE_AFTER_SUCCESSFUL_LOGIN).getText();
    }

    public void fillLoginFormWithRandomEmailAndPassword() {
        clickLoginButton();
        clickLoginButtonWithEmail();
        clickEmailField();
        sendRandomEmail(createRandomEmail());
        clickPasswordField();
        sendRandomPassword(createRandomPassword());
        clickVisibleButtonLogin();
    }

    public String getLoginErrorMessage() {
        waitAndSwitchToFrame();
        waitForVisibleOfElement(INVALID_LOGIN_ERROR);
        String errorText = driver.findElement(INVALID_LOGIN_ERROR).getText();
        driver.switchTo().defaultContent();
        return errorText;


    }
}
