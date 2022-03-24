package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.GoogleGoToPage;
import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.GoogleSearchResultPage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatNextAndPreviousLinkIsDisplayed() {
        String searchTerm = "funny dogs";
        GoogleSearchResultPage googleSearchResultPage = new GoogleHomePage()
                .search(searchTerm);

        assertNotNull(googleSearchResultPage.getNextLink(), "link Next is displayed");
        GoogleGoToPage googleGoToPage = new GoogleGoToPage()
                .goToPage(2);
        assertNotNull(googleGoToPage.getPreviousLink(), "link Previous is displayed");
    }
}
