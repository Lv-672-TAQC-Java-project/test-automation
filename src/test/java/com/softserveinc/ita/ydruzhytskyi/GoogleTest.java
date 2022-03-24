package com.softserveinc.ita.ydruzhytskyi;

import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatLinkHasHrefAttributeWithValidUrl_PO() {
        GoogleHomePage googleHomePage = new GoogleHomePage().open("https://google.com");
        String searchTerm = "funny dogs";
        String hrefValue = googleHomePage
                .search(searchTerm)
                .getHrefAttributeFromElement(9);

        assertTrue(hrefValue.contains("https://"));
    }
}
