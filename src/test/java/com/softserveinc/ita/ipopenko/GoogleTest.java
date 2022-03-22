package com.softserveinc.ita.ipopenko;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest {
    @Test
    public void verifyThatFirstLinkContainsPartOfTheSearchTerm() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10 * 1000;
        open("https://google.com");

        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@name='q']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("//a[@aria-label = 'Page 5']").click();

        $x("(//a[h3])[1]")
                .shouldHave(text("dogs")
                        .because("First link should contain dogs"));
    }
}
