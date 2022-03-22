package com.softserveinc.ita.ydruzhytskyi;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest {

    @Test
    public void verifyThatLinkHasHrefAttributeWithValidUrl() {
        Configuration.browser = "chrome";
        Configuration.timeout = 30 * 1000;
        open("https://google.com");

        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("(//div[@class='g dFd2Tb'])[9]/descendant::div[@class='ct3b9e']/a")
                .shouldHave(attributeMatching("href", "^https?:\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"));

    }
}
