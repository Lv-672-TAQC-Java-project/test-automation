package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.GoogleSearchResultPage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatNextAndPreviousLinkIsDisplayed() {
        String searchTerm = "funny dogs";

        GoogleSearchResultPage googleSearchResultPage = googleHomePage
                .search(searchTerm);

        assertTrue(googleSearchResultPage
                .isNextLinkDisplayed(), "link Next is displayed");
        googleSearchResultPage.goToPage(2);
        assertTrue(googleSearchResultPage
                .isPreviousLinkDisplayed(), "link Previous is displayed");
    }
}
