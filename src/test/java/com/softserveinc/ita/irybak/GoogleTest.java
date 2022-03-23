package com.softserveinc.ita.irybak;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyThatNextLinkIsDisplayed() {
        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@name = 'q']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("//*[@id='pnnext']/span[2]")
                .shouldBe(visible
                        .because("The next link should be displayed"));
    }
}
