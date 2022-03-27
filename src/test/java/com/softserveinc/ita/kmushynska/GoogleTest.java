package com.softserveinc.ita.kmushynska;

import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleHomePageIsOpen() {
        String searchTerm = "funny dogs";
        GoogleHomePage openedGoogleHomePage = new GoogleHomePage()
                .search(searchTerm)
                .backToHomePageByLogo();

        assertTrue(openedGoogleHomePage.isGoogleImgElementDisplayed());
        assertTrue(openedGoogleHomePage.isSearchInputElementDisplayed());
        assertTrue(openedGoogleHomePage.isButtonGoogleSearchDisplayed());
        assertTrue(openedGoogleHomePage.isButtonImFeelingLuckyDisplayed());
        assertTrue(openedGoogleHomePage.isGoogleOfferedInElementDisplayed());
    }
}
