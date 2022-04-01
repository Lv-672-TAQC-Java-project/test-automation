package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.GoogleSearchResultPage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatNextAndPreviousLinkIsDisplayed() {
        String searchTerm = "funny dogs";

        GoogleSearchResultPage googleSearchResultPage = googleHomePage
                .search(searchTerm);

        assertThat(googleSearchResultPage.isNextLinkDisplayed())
                .as("link Next should be displayed").isTrue();
        googleSearchResultPage.goToPage(2);
        assertThat(googleSearchResultPage.isPreviousLinkDisplayed())
                .as("link Previous should be displayed").isTrue();
    }
}
