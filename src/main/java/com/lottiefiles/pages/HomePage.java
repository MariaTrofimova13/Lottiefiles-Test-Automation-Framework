package com.lottiefiles.pages;

import com.lottiefiles.driver.Driver;
import com.lottiefiles.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final String BASE_URL = "https://lottiefiles.com/";

    private final By BY_COOKIE_ALERT_CLOSE = By.xpath("//button[@class='simple-cookie-banner__accept-btn']");
    private final By BY_BUTTON_LOGIN = By.xpath("//button[@class=\"cursor-pointer inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive border bg-background shadow-xs hover:bg-accent hover:text-accent-foreground dark:bg-input/30 dark:border-input dark:hover:bg-input/50 h-9 px-4 py-2 has-[>svg]:px-3 font-sans\"]");
    private final By BY_BUTTON_LOGIN_WITH_EMAIL = By.xpath("//button[@id=\"button-password-login\"]");
    private final By BY_EMAIL_BUTTON = By.xpath("//div[@class=\"flex flex-col gap-4\"]/div[1]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]");
    private final By BY_PASSWORD_BUTTON = By.xpath("//div[@class=\"flex flex-col gap-4\"]/div[2]/div[@class=\"has-[input:focus]:border-action-focus flex h-11 flex-1 items-center rounded-sm border-solid bg-gray-50 p-[12px] text-base border-default  border-subtle\"]");

    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void closeCookies() {
        driver.findElement(BY_COOKIE_ALERT_CLOSE).click();
    }

    public void clickLoginButton() {
        driver.findElement(BY_BUTTON_LOGIN).click();
    }

    public void clickLoginButtonWithEmail() {
        Waits.waitFor(1000);
        driver.findElement(BY_BUTTON_LOGIN_WITH_EMAIL).click();
    }
}