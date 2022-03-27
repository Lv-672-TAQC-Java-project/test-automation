package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleHomePage {
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

    public SelenideElement getGoogleImgElement() {

        return $x("//img[@class='lnXdpd']");
    }

    public SelenideElement getSearchInputElement() {

        return $x("//div[@class='RNNXgb']");
    }

    public SelenideElement getButtonGoogleSearch() {

        return $x("(//input[@class='gNO89b'])[2]");
    }

    public SelenideElement getButtonImFeelingLucky() {

        return $x("(//input[@class='RNmpXc'])[2]");
    }

    public SelenideElement getGoogleOfferedInElement() {

        return $x("//div[@id='SIvCob']");
    }
}
