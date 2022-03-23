package com.softserveinc.ita.dstoyko;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest {

    @Test
    public void verifyThatFirstLinkNameContainsKittenNotDogs() {
        Configuration.browser = "chrome";
        Configuration.timeout = 30 * 1000;
        open("https://google.com");

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
        String firstLink = "(//div[@class = 'g dFd2Tb']) [1]";
        $x(firstLink)
                .shouldBe(visible, enabled)
                .shouldNotHave(text("dogs")
                        .because("First link name should not contain 'dogs'"), duration)
                .shouldHave(text("kitten")
                        .because("First link name should contain 'kitten'"), duration);
    }
}