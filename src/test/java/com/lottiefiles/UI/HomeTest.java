package com.lottiefiles.UI;

import com.lottiefiles.driver.Driver;
import com.lottiefiles.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class HomeTest {
    private HomePage homePage;
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void openHomePageAndCloseCookies() {
        homePage = new HomePage()
                .open()
                .closeCookies();
    }

    @Test
    public void testTextInFooter() {
        logger.info("Тест: Проверка текста в футере страницы");
        Assertions.assertEquals(homePage.FOOTER_TEXT, homePage.getFooterText());
    }

    @Test
    public void topBannerPresenceTest(){
        logger.info("Тест: Проверка отображения и текста кнопок верхнего баннера");
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
