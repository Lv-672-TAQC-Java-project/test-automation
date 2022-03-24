package com.softserveinc.ita.mromanyshyn;

import com.softserveinc.ita.GoogleHomePage;
import com.softserveinc.ita.GoogleSearchResultPage;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.ENTER;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatFirstLinkContainsKittenNotDogs() {
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        String searchFirstTerm = "funny dogs";

        $x(inputFieldPath).sendKeys(searchFirstTerm);
        $x(inputFieldPath).sendKeys(ENTER);
        $x(inputFieldPath).clear();

        String searchSecondTerm = "funny kitten";

        $x(inputFieldPath).sendKeys(searchSecondTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        String expectedTerm = "kitten";
        String firstLinkPath = "(//div[@class = 'g dFd2Tb']) [1]";

        $x(firstLinkPath)
                .shouldBe(visible, enabled)
                .shouldNotHave(text("dogs"))
                .shouldHave(text(expectedTerm)
                        .because("First link text should contain " + expectedTerm), ofSeconds(5));
    }

    @Test
    public void verifyThatFirstLinkContainsKittenNotDogs_PO() {
        GoogleHomePage googleHomePage = new GoogleHomePage().open("https://www.google.com/");
        String firstTerm = "funny dogs";
        String secondTerm = "funny kitten";

        GoogleSearchResultPage googleSearchResultPage = googleHomePage
                .search(firstTerm)
                .clearSearchField()
                .search(secondTerm);

        String linkText = googleSearchResultPage.getTextFromLink(1);

        assertFalse(linkText.contains("dogs"));
        assertTrue(linkText.contains("kitten"));
    }
}
