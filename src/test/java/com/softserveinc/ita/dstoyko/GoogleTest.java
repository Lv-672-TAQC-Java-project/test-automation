package com.softserveinc.ita.dstoyko;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

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
        webdriver().driver().getWebDriver().manage().window().maximize();

        String searchFirstTerm = "funny dogs";
        String searchSecondTerm = "funny kitten";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        String firstLink = "(//div[@class = 'g dFd2Tb']) [1]";

        $x(inputFieldPath).sendKeys(searchFirstTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x(inputFieldPath).clear();
        $x(inputFieldPath).shouldBe(empty);

        $x(inputFieldPath).sendKeys(searchSecondTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x(firstLink)
                .shouldBe(visible, enabled)
                .shouldNotHave(text("dogs")
                        .because("First link name should not contain 'dogs'"), ofSeconds(4))
                .shouldHave(text("kitten")
                        .because("First link name should contain 'kitten'"), ofSeconds(4));
    }
}