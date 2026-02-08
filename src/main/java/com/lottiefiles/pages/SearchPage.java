package com.lottiefiles.pages;

import com.lottiefiles.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchPage {
    private final String SEARCH_PAGE_URL = "https://lottiefiles.com/free-animations/";
    private final String SEATCH_FIELD = "//div[@class=\"grid w-full grid-cols-1 max-w-[208px] relative\"]";
    private final String SEARCH_INPUT_FIELD = "//input[@data-slot=\"command-input\"]";
    private final String SEARCH_RESULT_TITLE = "//span[@class=\"capitalize\"]";
    private final String SEARCH_MAIN_BANNER = "//h1[@class=\"mb-4 lg:mb-6 max-w-[720px] mx-auto heading4 lg:heading2 font-dm text-left sm:text-center\"]";
    private final String MORE_SEARCH_BANNER = "//h2[@class=\"mb-4 md:mb-6 heading4 text-center md:heading3 capitalize\"]";
    private final String PACKS_SEARCH_BANNER = "//h2[@class=\"mb-4 md:mb-6 heading3 capitalize\"]";

    private static final Logger logger = LogManager.getLogger();

    public void clickForSearchField() {
        logger.info("Кликаем по полю поиска");
        Driver.click(SEATCH_FIELD);
    }

    public void inputSearchTextAndPressEnter(String searchText) {
        logger.info("Вводим текст поиска: '{}'", searchText);
        Driver.waitAndInput(SEARCH_INPUT_FIELD, searchText);
        logger.info("Нажимаем Enter для поиска");
        Driver.PressEnter(SEARCH_INPUT_FIELD);
    }

    private String capitalFirstLetter(String searchText) {
        return searchText.substring(0, 1).toUpperCase() + searchText.substring(1);
    }

    public String getExpectedSearchResaltText(String searchText) {
        return String.format("Free %s Animations", capitalFirstLetter(searchText));
    }

    public String getExpectedSearchMainBannerText(String searchText){
        return String.format("Free %s Animations", capitalFirstLetter(searchText));
    }

    public String getExpectedMoreSearchBannerText(String searchText){
        return String.format("More %s Animations", capitalFirstLetter(searchText));
    }

    public String getExpectedPacksSearchBannerText(String searchText){
        return String.format("%s Animation Packs", capitalFirstLetter(searchText));
    }

    public void startSearching(String searchParam) {
        String url = SEARCH_PAGE_URL + searchParam;
        logger.info("Переход на страницу поиска по URL: {}", url);
        Driver.goToPage(url);
    }

    public String getSearchResultTitle() {
        return Driver.getText(SEARCH_RESULT_TITLE);
    }

    public String getSearchMainBannerText(){
        return Driver.getText(SEARCH_MAIN_BANNER);
    }

    public String getMoreSearchBannerText(){
        return Driver.getText(MORE_SEARCH_BANNER);
    }

    public String getPacksSearchBannerText(){
        return Driver.getText(PACKS_SEARCH_BANNER);
    }
}
