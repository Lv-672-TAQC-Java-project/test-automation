package com.softserveinc.ita.RomanVytrykush;

import com.codeborne.selenide.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleTest {

    @Test
    public void verifyThatGoogleLogoIsPresent () {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Selenide.open("https://www.google.com/");

        $x("//*[@name = 'q']").sendKeys("funny dogs");
        $x("//*[@name = 'q']").pressEnter();
        $x("//*[@id = 'logo']").shouldBe(Condition.visible);

        WebDriverRunner.getWebDriver().quit();
    }
}
