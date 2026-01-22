package com.lottiefiles.pages;

import com.lottiefiles.driver.Driver;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final String BASE_URL = "https://lottiefiles.com/";

    private final String BUTON_COOKIE_CLOSE = "//button[@class='simple-cookie-banner__accept-btn']";
    private final String BUTTON_LOGIN = "//button[@class=\"cursor-pointer inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive border bg-background shadow-xs hover:bg-accent hover:text-accent-foreground dark:bg-input/30 dark:border-input dark:hover:bg-input/50 h-9 px-4 py-2 has-[>svg]:px-3 font-sans\"]";
    private final String FOOTER = "//p[@class=\"text-center md:text-left text-muted-foreground-inverse font-dm text-sm\"]";

    public final String FOOTER_TEXT = "© 2026 Design Barn Inc.";

    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    public HomePage open() {
        driver.get(BASE_URL);
        return this;
    }

    public HomePage closeCookies() {
        Driver.click(BUTON_COOKIE_CLOSE);
        return this;
    }

    public String getFooterText() {
        return Driver.getText(FOOTER);
    }

    public LoginPage clickLoginButton() {
        Driver.click(BUTTON_LOGIN);
        return new LoginPage();
    }


}