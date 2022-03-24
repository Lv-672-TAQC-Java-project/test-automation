package com.softserveinc.ita.rvytrykush;

import com.softserveinc.ita.GoogleSearchResultPage;
import com.softserveinc.ita.TestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleLogoIsPresent() {
        GoogleSearchResultPage searchResultPage = new GoogleSearchResultPage();
        String searchLine = "funny dogs";
        WebElement googleLogo = searchResultPage.getGoogleLogo();

        searchResultPage
                .search(searchLine);

        Assert.assertTrue(googleLogo.isDisplayed());
    }
}
