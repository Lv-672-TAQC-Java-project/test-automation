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

        String searchTerm = "funny kitten";
        String inputFieldPath = "//input[@name = 'q']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("//h2[@class ='Uo8X3b OhScic zsYMMe']/..//div[@class = 'ct3b9e']//h3[@class = 'LC20lb MBeuO DKV0Md']")
                .shouldBe(visible);
    }
}
