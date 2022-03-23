package com.softserveinc.ita.ydruzhytskyi;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatLinkHasHrefAttributeWithValidUrl() {
        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("(//div[@class='g dFd2Tb'])[9]/descendant::div[@class='ct3b9e']/a")
                .shouldHave(attribute("href"))
                .shouldHave(text("https://")
                        .because("URL should be valid"));
    }
}
