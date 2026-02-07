package com.lottiefiles.UI;

import com.lottiefiles.driver.Driver;
import com.lottiefiles.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class HomeTest {
    private HomePage homePage;

    @BeforeEach
    public void openHomePageAndCloseCookies() {
        homePage = new HomePage()
                .open()
                .closeCookies();
    }

    @Test
    public void testTextInFooter() {
        Assertions.assertEquals(homePage.FOOTER_TEXT, homePage.getFooterText());
    }

    @Test
    public void topBannerPresenceTest(){
        assertAll(
                () -> Assertions.assertEquals(homePage.BUTTON_PRODUCTS_TEXT, homePage.getButtonProductsText()),
                () -> Assertions.assertEquals(homePage.BUTTON_INTEGRATIONS_TEXT, homePage.getButtonIntegrationsText()),
                () -> Assertions.assertEquals(homePage.BUTTON_TOOLS_TEXT, homePage.getButtonToolsText()),
                () -> Assertions.assertEquals(homePage.BUTTON_CUSTOMERS_TEXT, homePage.getButtonCustomersText()),
                () -> Assertions.assertEquals(homePage.BUTTON_LEARN_TEXT, homePage.getButtonLearnText()),
                () -> Assertions.assertEquals(homePage.BUTTON_PRICING_TEXT, homePage.getButtonPricingText())
        );
    }

    @AfterEach
    public void closeBrowser() {
        Driver.quit();
    }

}
