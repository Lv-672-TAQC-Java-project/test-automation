package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;

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

    public boolean isGoogleImgElementDisplayed() {
        String googleImgElement = "//img[@class='lnXdpd']";

        return $x(googleImgElement).isDisplayed();
    }

    public boolean isSearchInputElementDisplayed() {
        String searchInputElement = "//div[@class='RNNXgb']";

        return $x(searchInputElement).isDisplayed();
    }

    public boolean isButtonGoogleSearchDisplayed() {
        String buttonGoogleSearch = "(//input[@class='gNO89b'])[2]";

        return $x(buttonGoogleSearch).isDisplayed();
    }

    public boolean isButtonImFeelingLuckyDisplayed() {
        String buttonImFeelingLucky = "(//input[@class='RNmpXc'])[2]";

        return $x(buttonImFeelingLucky).isDisplayed();
    }

    public boolean isGoogleOfferedInElementDisplayed() {
        String googleOfferedInElement = "//div[@id='SIvCob']";

        return $x(googleOfferedInElement).isDisplayed();
    }
}
