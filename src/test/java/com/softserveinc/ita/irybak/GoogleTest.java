package com.softserveinc.ita.irybak;

import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyThatNextLinkIsDisplayed() {
        String searchTerm = "funny dogs";
        boolean isNextLink = new GoogleHomePage()
                .search(searchTerm)
                .isNextLinkDisplayed();

        assertTrue(isNextLink);
    }
}
