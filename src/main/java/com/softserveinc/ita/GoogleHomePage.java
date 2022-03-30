package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleHomePage {

    @Step("Opened Google home page")
    public GoogleHomePage open() {
        Selenide.open("https://www.google.com/");

        return this;
    }

    public GoogleSearchResultPage search(String term) {
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        $x(inputFieldPath).sendKeys(term);
        $x(inputFieldPath).sendKeys(ENTER);

        return new GoogleSearchResultPage();
    }
}
