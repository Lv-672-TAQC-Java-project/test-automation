package com.softserveinc.ita.ydruzhytskyi;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatLinkHasHrefAttributeWithValidUrl() {
        String searchTerm = "funny dogs";
        boolean isValidLink = googleHomePage
                .search(searchTerm)
                .hasValidLink(9);

        assertTrue(isValidLink, "URL should be valid");
    }
}
