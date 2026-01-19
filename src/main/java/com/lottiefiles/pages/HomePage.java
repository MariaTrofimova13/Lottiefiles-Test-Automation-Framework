package com.lottiefiles.pages;

import com.github.javafaker.Faker;
import com.lottiefiles.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final String BASE_URL = "https://lottiefiles.com/";
    private final By IFRAME = By.id("auth-client-iframe");

    public final String LOGIN_TITLE_TEXT = "Log in";
    public final String INVALID_LOGIN_ERROR_TEXT = "Incorrect email or password. Please enter them again.";

    private final By COOKIE_ALERT_CLOSE = By.xpath("//button[@class='simple-cookie-banner__accept-btn']");
    private final By BUTTON_LOGIN = By.xpath("//button[@class=\"cursor-pointer inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive border bg-background shadow-xs hover:bg-accent hover:text-accent-foreground dark:bg-input/30 dark:border-input dark:hover:bg-input/50 h-9 px-4 py-2 has-[>svg]:px-3 font-sans\"]");
    private final By BUTTON_LOGIN_WITH_EMAIL = By.xpath("//button[@id=\"button-password-login\"]/div[@class=\"flex justify-center items-center w-icon-lg h-icon-lg *:w-icon-lg *:h-icon-lg\"]");
    private final By LOGIN_TITLE = By.xpath("//h2[@class=\"font-bold text-2xl\"]");
    private final By EMAIL_FIELD = By.xpath("//div[@class=\"flex flex-col gap-4\"]/div[1]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]");
    private final By EMAIL_INPUT = By.xpath("//div[@class=\"flex flex-col gap-4\"]//input[@placeholder=\"Email\"]");
    private final By PASSWORD_FIELD = By.xpath("//div[@class=\"flex flex-col gap-4\"]/div[2]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]");
    private final By PASSWORD_INPUT = By.xpath("//div[@class=\"flex flex-col gap-4\"]//input[@placeholder=\"Password\"]");
    private final By VISIBLE_BUTTON_LOGIN = By.xpath("//button[@id=\"button-login-submit\"]");
    private final By INVALID_LOGIN_ERROR = By.xpath("//p[@class=\"text-sm\"]");

    private WebDriver driver;
    private WebDriverWait wait;
    private Faker faker;

    public HomePage() {
        this.driver = Driver.getDriver();
        this.faker = new Faker();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebElement waitForVisibleOfElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitAndSwitchToFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IFRAME));
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void closeCookies() {
        driver.findElement(COOKIE_ALERT_CLOSE).click();
    }

    public void clickLoginButton() {
        driver.findElement(BUTTON_LOGIN).click();
    }

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