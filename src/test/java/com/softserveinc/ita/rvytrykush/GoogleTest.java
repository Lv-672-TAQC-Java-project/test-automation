package com.softserveinc.ita.rvytrykush;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleLogoIsPresent() {
        String searchLine = "funny dogs";

        boolean checkVisibilityOfGoogleLogo = googleHomePage
                .search(searchLine)
                .isGoogleLogoVisible();

        assertTrue(checkVisibilityOfGoogleLogo);
    }
}
