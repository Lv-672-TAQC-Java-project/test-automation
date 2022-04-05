package com.softserveinc.ita.deprecated.kmushynska;

import com.softserveinc.ita.deprecated.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleHomePageIsOpen() {
        String searchTerm = "funny dogs";
        googleHomePage
                .search(searchTerm)
                .backToHomePageByLogo();

        assertThat(googleHomePage.isGoogleLogoDisplayed())
                .as("Google logo should be displayed")
                .isTrue();
        assertThat(googleHomePage.isSearchInputFieldDisplayed())
                .as("Search input field should be displayed")
                .isTrue();
        assertThat(googleHomePage.isButtonGoogleSearchDisplayed())
                .as("Button 'Google Search' should be displayed")
                .isTrue();
        assertThat(googleHomePage.isButtonImFeelingLuckyDisplayed())
                .as("Button 'I'm Feeling Lucky' should be displayed")
                .isTrue();
        assertThat(googleHomePage.isGoogleLanguageFieldDisplayed())
                .as("Google language field should be displayed")
                .isTrue();
    }
}
