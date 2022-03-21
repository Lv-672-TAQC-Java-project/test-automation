package com.softserveinc.ita.mromanyshyn;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverConditions;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest {

    @Test
    public void verifyThatFirstLinkContainsKittenNotDogs() {
        Configuration.browser = "chrome";
        Configuration.timeout = 20 * 1000;
        open("https://google.com");

        String searchFirstTerm = "funny dogs";
        String searchSecondTerm = "funny kitten";
        String expectedTerm = "kitten";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        String firstLink = "(//div[@class = 'g dFd2Tb']) [1]";

        $x(inputFieldPath).sendKeys(searchFirstTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x(inputFieldPath).clear();

        $x(inputFieldPath).sendKeys(searchSecondTerm);
        $x(inputFieldPath).sendKeys(ENTER);
        
        $x(firstLink)
                .shouldBe(visible, enabled)
                .shouldNotHave(text("dogs"))
                .shouldHave(text(expectedTerm)
                        .because("First link text should contain " + expectedTerm), ofSeconds(2));

        closeWindow();
        closeWebDriver();
    }
}
