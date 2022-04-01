package com.softserveinc.ita.rvytrykush;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleLogoIsVisible() {
        String searchLine = "funny dogs";

        boolean isGoogleLogoVisible = googleHomePage
                .search(searchLine)
                .isGoogleLogoVisible();

        assertThat(isGoogleLogoVisible)
                .as("Google logo on the 'GoogleSearchResultPage' should be visible")
                .isTrue();
    }
}
