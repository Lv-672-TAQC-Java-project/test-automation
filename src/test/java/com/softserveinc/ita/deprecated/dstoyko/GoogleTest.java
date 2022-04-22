package com.softserveinc.ita.deprecated.dstoyko;

import com.softserveinc.ita.deprecated.GoogleSearchResultPage;
import com.softserveinc.ita.deprecated.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatFirstLinkNameContainsKittenNotDogs() {
        String searchFirstTerm = "funny dogs";
        String searchSecondTerm = "funny kitten";

        GoogleSearchResultPage searchResultPage = googleHomePage
                .search(searchFirstTerm)
                .clearSearchField()
                .search(searchSecondTerm);

        String linkText = searchResultPage.getTextFromLink(1);

        String expectedString = "kitten";
        String unexpectedString = "dogs";
        assertThat(linkText)
                .as("Link text should contain " + expectedString)
                .contains(expectedString)
                .as("Link text should not contain " + unexpectedString)
                .doesNotContain(unexpectedString);
    }
}