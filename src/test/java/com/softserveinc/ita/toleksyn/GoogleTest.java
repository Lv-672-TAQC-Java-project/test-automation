package com.softserveinc.ita.toleksyn;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.ENTER;

/*
This is a simple Selenide usage example, to be deleted
 */
public class GoogleTest {

    @Test
    public void verifyThatSearchingDisplaysResults() {
        // open google home page
        // search for 'funny kitten'
        // verify that first link contains word 'kitten'
        Configuration.browser = "chrome";
        Configuration.timeout = 30 * 1000;
        open("https://google.com");

        String searchTerm = "funny kitten";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("(//a/h3)[1]")
                .shouldBe(visible, enabled)
                .shouldHave(text(searchTerm)
                        .because("Second link text should contain " + searchTerm), ofSeconds(4));
    }
}
