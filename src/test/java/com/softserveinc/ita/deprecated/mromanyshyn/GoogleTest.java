package com.softserveinc.ita.deprecated.mromanyshyn;

import com.softserveinc.ita.deprecated.GoogleSearchResultPage;
import com.softserveinc.ita.deprecated.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatFirstLinkContainsKittenNotDogs_PO() {
        String firstTerm = "funny dogs";
        String secondTerm = "funny kitten";

        GoogleSearchResultPage googleSearchResultPage = googleHomePage
                .search(firstTerm)
                .clearSearchField()
                .search(secondTerm);

        String linkText = googleSearchResultPage.getTextFromLink(1);
        String notExpectedTerm = "dogs";
        String expectedTerm = "kitten";

        assertThat(linkText)
                .as("First link text should not contain " + notExpectedTerm)
                .doesNotContain(notExpectedTerm)
                .as("First link text should not contain " + expectedTerm)
                .contains(expectedTerm);
    }
}
