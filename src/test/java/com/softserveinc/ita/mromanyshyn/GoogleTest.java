package com.softserveinc.ita.mromanyshyn;

import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.GoogleSearchResultPage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatFirstLinkContainsKittenNotDogs_PO() {
        GoogleHomePage googleHomePage = new GoogleHomePage().open();
        String firstTerm = "funny dogs";
        String secondTerm = "funny kitten";

        GoogleSearchResultPage googleSearchResultPage = googleHomePage
                .search(firstTerm)
                .clearSearchField()
                .search(secondTerm);

        String linkText = googleSearchResultPage.getTextFromLink(1);

        assertFalse(linkText.contains("dogs"));
        assertTrue(linkText.contains("kitten"));
    }
}
