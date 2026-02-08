package com.lottiefiles.UI;

import com.lottiefiles.driver.Driver;
import com.lottiefiles.pages.HomePage;
import com.lottiefiles.pages.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {
    private HomePage homePage;
    private SearchPage searchPage;
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void openHomePageAndCloseCookies() {
        homePage = new HomePage();
        homePage.open();
        homePage.closeCookies();

        searchPage = new SearchPage();
    }

    @Test
    public void searchTest() {
        String searchText = "cat";
        searchPage.clickForSearchField();
        searchPage.inputSearchTextAndPressEnter(searchText);

        assertAll("banner checking",
                () -> Assertions.assertEquals(searchPage.getExpectedSearchResaltText(searchText), searchPage.getSearchResultTitle()),
                () -> Assertions.assertEquals(searchPage.getExpectedSearchMainBannerText(searchText), searchPage.getSearchMainBannerText()),
                () -> Assertions.assertEquals(searchPage.getExpectedMoreSearchBannerText(searchText), searchPage.getMoreSearchBannerText()),
                () -> Assertions.assertEquals(searchPage.getExpectedPacksSearchBannerText(searchText), searchPage.getPacksSearchBannerText())
        );
    }

    @Test
    public void searchWithUrlTest() {
        String searchWord = "dog";
        searchPage.startSearching(searchWord);

        Assertions.assertEquals(searchPage.getExpectedSearchResaltText(searchWord), searchPage.getSearchResultTitle());
    }

    @AfterEach
    public void closeBrowser() {
        Driver.quit();
    }
}
