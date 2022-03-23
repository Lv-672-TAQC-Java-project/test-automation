package com.softserveinc.ita.mromanyshyn;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest {

    @Test
    public void verifyThatFirstLinkContainsKittenNotDogs() {
        var testRunner = new TestRunner();
        testRunner.driverConfigurationAndTimeout();
        testRunner.navigateToUrl();

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
}