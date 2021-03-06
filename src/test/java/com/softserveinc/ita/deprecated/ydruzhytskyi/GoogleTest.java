package com.softserveinc.ita.deprecated.ydruzhytskyi;

import com.softserveinc.ita.deprecated.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatLinkHasHrefAttributeWithValidUrl() {
        String searchTerm = "funny dogs";
        boolean isValidLink = googleHomePage
                .search(searchTerm)
                .hasValidLink(9);

        assertThat(isValidLink)
                .as("URL should be valid")
                .isTrue();
    }
}
