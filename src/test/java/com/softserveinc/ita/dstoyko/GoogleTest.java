package com.softserveinc.ita.dstoyko;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatFirstLinkNameContainsKittenNotDogs() {
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        String searchFirstTerm = "funny dogs";
        $x(inputFieldPath).sendKeys(searchFirstTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x(inputFieldPath).clear();
        $x(inputFieldPath).shouldBe(empty);

        String searchSecondTerm = "funny kitten";
        $x(inputFieldPath).sendKeys(searchSecondTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        Duration duration = ofSeconds(4);
        String firstLinkPath = "(//div[@class = 'g dFd2Tb']) [1]";
        $x(firstLinkPath)
                .shouldBe(visible, enabled)
                .shouldNotHave(text("dogs")
                        .because("First link name should not contain 'dogs'"), duration)
                .shouldHave(text("kitten")
                        .because("First link name should contain 'kitten'"), duration);
    }
}