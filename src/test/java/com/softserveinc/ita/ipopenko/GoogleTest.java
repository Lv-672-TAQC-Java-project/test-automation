package com.softserveinc.ita.ipopenko;

import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.GoogleSearchResultPage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyThatFirstLinkContainsPartOfTheSearchTerm() {
        String searchTerm = "funny dogs";
        GoogleSearchResultPage googleSearchResultPage = new GoogleHomePage()
                .search(searchTerm);

        String linkText = googleSearchResultPage
                .goToPage(5)
                .getTextFromLink(1);

        assertTrue(linkText.contains("dogs"), "First link should contain dogs");
    }
}
