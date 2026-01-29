package com.lottiefiles.pages;

import com.lottiefiles.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private final String SEARCH_PAGE_URL = "https://lottiefiles.com/free-animations/";

    public final By SEARCH_RESULT_TITLE = By.xpath("//div[@class=\"text-sm font-medium text-muted-foreground\"]");

    private WebDriver driver;

    public SearchPage() {
        this.driver = Driver.getDriver();
    }

    public void startSearching(String searchParam) {
        driver.get(SEARCH_PAGE_URL + searchParam);
    }

    public String getSearchResultTitle() {
        return driver.findElement(SEARCH_RESULT_TITLE).getText();
    }


}
