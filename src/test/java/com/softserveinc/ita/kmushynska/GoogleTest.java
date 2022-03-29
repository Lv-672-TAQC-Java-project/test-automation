package com.softserveinc.ita.kmushynska;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleHomePageIsOpen() {
        String searchTerm = "funny dogs";
        googleHomePage
                .search(searchTerm)
                .backToHomePageByLogo();

        assertTrue(googleHomePage.isGoogleLogoDisplayed());
        assertTrue(googleHomePage.isSearchInputFieldDisplayed());
        assertTrue(googleHomePage.isButtonGoogleSearchDisplayed());
        assertTrue(googleHomePage.isButtonImFeelingLuckyDisplayed());
        assertTrue(googleHomePage.isGoogleLanguageFieldDisplayed());
    }
}
