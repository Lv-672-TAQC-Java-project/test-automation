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

        $x("//a[@aria-label = 'Page 2']").shouldBe(Condition.visible, Duration.ofSeconds(20));
        $x("//a[@aria-label = 'Page 4']").click();
        $x("//a[@aria-label = 'Page 1']").shouldBe(Condition.visible, Duration.ofSeconds(20));

    }
}
