package com.softserveinc.ita.OlehPysko;

import com.codeborne.selenide.*;
import org.testng.annotations.Test;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;


public class GoogleTest {
    @Test
    public void verifyThatNextAndPreviousLinkIsDisplayed(){

        Configuration.browser = "chrome";
        Selenide.open("https://google.com");

        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";

        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("//span[contains(text(),'Next')]").isDisplayed();
        $x("//a[@aria-label = 'Page 4']").click();
        $x("//span[contains(text(),'Previous')]").isDisplayed();

    }
}
