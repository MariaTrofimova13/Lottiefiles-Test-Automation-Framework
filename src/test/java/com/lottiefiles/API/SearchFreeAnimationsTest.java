package com.lottiefiles.API;

import com.lottiefiles.api.SearchService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SearchFreeAnimationsTest {
    private SearchService searchService;
    String searchQuery = "cat";

    @Test
    public void testSearchPictureLoading() {
        searchService = new SearchService();
        searchService.setSearchQuery(searchQuery);
        searchService.doRequest();

        assertAll("searchService",
                () -> assertEquals(200, searchService.getStatusCode()),
                () -> assertTrue(searchService.getBody().contains(searchService.getExpectedText(searchQuery)), "Текст не соответствует фразе Free поисковое слово Animations")
        );
    }
}