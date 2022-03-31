package com.softserveinc.ita.irybak;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyThatNextLinkIsDisplayed() {
        String searchTerm = "funny dogs";
        boolean isNextLinkDisplayed = googleHomePage
                .search(searchTerm)
                .isNextLinkDisplayed();

        assertThat(isNextLinkDisplayed)
                .as("The next link should be displayed")
                .isTrue();
    }
}
