package com.softserveinc.ita.kmushynska;

import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleHomePageIsOpen() {
        String searchTerm = "funny dogs";
        GoogleHomePage openedGoogleHomePage = new GoogleHomePage()
                .search(searchTerm)
                .backToHomePageByLogo();

        Assert.assertTrue(openedGoogleHomePage.getGoogleImgElement().isDisplayed());
        Assert.assertTrue(openedGoogleHomePage.getSearchInputElement().isDisplayed());
        Assert.assertTrue(openedGoogleHomePage.getButtonGoogleSearch().isDisplayed());
        Assert.assertTrue(openedGoogleHomePage.getButtonImFeelingLucky().isDisplayed());
        Assert.assertTrue(openedGoogleHomePage.getGoogleOfferedInElement().isDisplayed());
    }
}
