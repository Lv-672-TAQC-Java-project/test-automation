package com.softserveinc.ita.irybak;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest {
    @Test
    public void verifyThatNextLinkIsDisplayed(){
        Configuration.browser = "chrome";
        Configuration.timeout = 30 * 1000;
        open("https://google.com");

        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@name = 'q']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("//*[@id='pnnext']/span[2]")
                .shouldBe(visible
                        .because("The next link should be displayed"));
    }
}
