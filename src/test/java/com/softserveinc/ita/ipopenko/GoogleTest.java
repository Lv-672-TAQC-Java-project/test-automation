package com.softserveinc.ita.ipopenko;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyThatFirstLinkContainsPartOfTheSearchTerm() {
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
