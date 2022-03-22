package com.softserveinc.ita.kmushynska;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest {

    @Test
    public void verifyThatGoogleHomePageIsOpen() {
        Configuration.browser = "Chrome";
        Configuration.timeout = 20 * 1000;
        open("https://google.com");
        WebDriverRunner.getWebDriver().manage().window().maximize();

        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("//div[@class='logo']").click();

        $x("//title")
                .shouldHave(attribute("text", "Google"));
    }
}
