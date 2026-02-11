package com.lottiefiles.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;
    private static WebDriverWait wait;
    static int DEFAULT_WAIT_TIME_SECONDS = 10;
    private static final Logger logger = LogManager.getLogger();

    private Driver() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME_SECONDS));
        logger.info("Создан хром драйвер");
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver завершил работу и был закрыт");
        }
    }

    private static WebElement waitForVisibilityOfElement(String xpath) {
        logger.info("Ожидание отображения элемента");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    private static WebElement waitAndSwitchToFrameAndWaitForVisibilityOfElement(String IdForIframe, String xpathForElement) {
        logger.info("Ожидание и переключение на iframe");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IdForIframe));
        logger.info("Ожидание видимости элемента внутри iframe");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathForElement)));
    }

    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        logger.info("Переключение кода к главному контенту");
    }

    public static void click(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void PressEnter(String xpath) {
        driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
    }

    public static void waitAndClick(String xpath) {
        waitForVisibilityOfElement(xpath).click();
    }

    public static void switchToFrameAndWaitAndClick(String IdForIframe, String xpathForElement) {
        logger.info("Переключение на iframe и клик по элементу");
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

    public static void goToPage(String url) {
        driver.get(url);
    }

    public static boolean isElementDisplayed(String xpath){
        driver.findElement(By.xpath(xpath)).isDisplayed();
        return true;
    }
}
