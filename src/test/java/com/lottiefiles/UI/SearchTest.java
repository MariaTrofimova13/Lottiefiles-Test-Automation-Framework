package com.lottiefiles.UI;

import com.lottiefiles.dataGenerator.*;
import com.lottiefiles.driver.*;
import com.lottiefiles.pages.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {
    private HomePage homePage;
    private SearchPage searchPage;

    @BeforeEach
    public void openHomePageAndCloseCookies() {
        homePage = new HomePage();
        homePage.open();
        homePage.closeCookies();

        searchPage = new SearchPage();
    }

    @Test
    public void searchTest() {
        String searchText = DataGenerator.generateRandomWord();
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
        String searchWord = DataGenerator.generateRandomWord();
        searchPage.startSearching(searchWord);

        Assertions.assertEquals(searchPage.getExpectedSearchResaltText(searchWord), searchPage.getSearchResultTitle());
    }

    @AfterEach
    public void closeBrowser() {
        Driver.quit();
    }
}
