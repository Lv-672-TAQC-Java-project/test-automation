package com.softserveinc.ita.rvytrykush;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleLogoIsVisible() {
        String searchLine = "funny dogs";

        boolean isVisible = googleHomePage
                .search(searchLine)
                .isGoogleLogoVisible();

        assertTrue(isVisible);
    }
}
