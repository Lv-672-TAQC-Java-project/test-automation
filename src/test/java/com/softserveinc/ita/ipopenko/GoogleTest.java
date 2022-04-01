package com.softserveinc.ita.ipopenko;

import com.softserveinc.ita.GoogleSearchResultPage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyThatFirstLinkContainsPartOfTheSearchTerm() {
        String searchTerm = "funny dogs";
        GoogleSearchResultPage googleSearchResultPage = googleHomePage
                .search(searchTerm);

        String linkText = googleSearchResultPage
                .goToPage(5)
                .getTextFromLink(1);

        String expectedString = "dogs";
        assertThat(linkText)
                .as("First link should contain " + expectedString)
                .contains(expectedString);
    }
}
