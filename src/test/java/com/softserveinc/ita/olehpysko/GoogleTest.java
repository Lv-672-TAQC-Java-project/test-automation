package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatNextAndPreviousLinkIsDisplayed() {
        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("(//a[@id='pnnext']/span)[2]").shouldBe(visible.because("link Next is displayed"));
        $x("//a[@aria-label = 'Page 4']").click();
        $x("(//a[@id='pnprev']/span)[2]").shouldBe(visible.because("link Previous is displayed"));
    }
}
