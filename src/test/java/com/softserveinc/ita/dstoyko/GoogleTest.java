package com.softserveinc.ita.dstoyko;

import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.GoogleSearchResultPage;
import com.softserveinc.ita.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatFirstLinkNameContainsKittenNotDogs() {
        GoogleHomePage googleHomePage = new GoogleHomePage().open();
        String searchFirstTerm = "funny dogs";
        String searchSecondTerm = "funny kitten";

        GoogleSearchResultPage searchResultPage = googleHomePage
                .search(searchFirstTerm)
                .clearSearchField()
                .search(searchSecondTerm);

        String linkText = searchResultPage.getTextFromLink(1);

        Assert.assertTrue(linkText.contains("kitten"), "First link should contain kitten");
        Assert.assertFalse(linkText.contains("dogs"), "First link should not contain dogs");
    }
}