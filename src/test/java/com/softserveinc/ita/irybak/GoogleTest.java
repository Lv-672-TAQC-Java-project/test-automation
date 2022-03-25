package com.softserveinc.ita.irybak;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyThatNextLinkIsDisplayed() {
        String searchTerm = "funny dogs";
        SelenideElement nextLink = new GoogleHomePage()
                .search(searchTerm)
                .getNextLink();

        assertTrue(nextLink.isDisplayed());
    }
}
