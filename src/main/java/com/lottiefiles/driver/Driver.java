package com.lottiefiles.driver;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Faker faker;

    private Driver() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static WebElement waitForVisibilityOfElement(String xpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    private static WebElement waitAndSwitchToFrameAndWaitForVisibilityOfElement(String IdForIframe, String xpathForElement) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IdForIframe));
         return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathForElement)));
    }

    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public static void click(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void waitAndClick(String xpath) {
        waitForVisibilityOfElement(xpath).click();
    }

    public static void switchToFrameAndWaitAndClick(String IdForIframe, String xpathForElement) {
        waitAndSwitchToFrameAndWaitForVisibilityOfElement(IdForIframe, xpathForElement).click();
    }

    public static void input(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    public static void waitAndInput(String xpath, String text) {
        waitForVisibilityOfElement(xpath).sendKeys(text);
    }

    public static void switchToFrameAndWaitAndInput(String IdForIframe, String xpathForElement, String text) {
       WebElement element = waitAndSwitchToFrameAndWaitForVisibilityOfElement(IdForIframe, xpathForElement);
       element.clear();
       element.sendKeys(text);

    }

    public static String getText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public static String waitAndGetText(String xpath) {
        return waitForVisibilityOfElement(xpath).getText();
    }

    public static String switchToFrameAndWaitAndGetText(String IdForIframe, String xpathForElement) {
        String text = waitAndSwitchToFrameAndWaitForVisibilityOfElement(IdForIframe, xpathForElement).getText();
        switchToDefaultContent();
        return text;
    }

    public static String createRandomEmail() {
        faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String createRandomPassword() {
        faker = new Faker();
        return faker.internet().password(8, 16);
    }
}
