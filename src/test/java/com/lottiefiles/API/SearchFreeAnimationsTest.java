package com.lottiefiles.API;

import com.lottiefiles.api.SearchService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SearchFreeAnimationsTest {
    @Test
    public void testSearchPictureLoading() {
        String searchQuery = "cat";
        SearchService searchService = new SearchService();
        searchService.setSearchQuery(searchQuery);
        searchService.doRequest();

        assertAll("searchService",
                () -> assertEquals(200, searchService.getStatusCode()),
                () -> assertTrue(searchService.getBody().contains("Free" + " " + searchService.capitalFirstLetter(searchQuery) + " " +  "Animations"), "Текст не соответствует фразе Free Loading Animations")
        );
    }
}