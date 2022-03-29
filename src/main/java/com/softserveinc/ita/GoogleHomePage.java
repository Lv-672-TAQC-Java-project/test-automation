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

    public boolean isGoogleLogoDisplayed() {
        String googleLogoPath = "//img[@class='lnXdpd']";

        return $x(googleLogoPath).isDisplayed();
    }

    public boolean isSearchInputFieldDisplayed() {
        String searchInputFieldPath = "//div[@class='RNNXgb']";

        return $x(searchInputFieldPath).isDisplayed();
    }

    public boolean isButtonGoogleSearchDisplayed() {
        String buttonGoogleSearchPath = "(//input[@class='gNO89b'])[2]";

        return $x(buttonGoogleSearchPath).isDisplayed();
    }

    public boolean isButtonImFeelingLuckyDisplayed() {
        String buttonImFeelingLuckyPath = "(//input[@class='RNmpXc'])[2]";

        return $x(buttonImFeelingLuckyPath).isDisplayed();
    }

    public boolean isGoogleOfferedInElementDisplayed() {
        String googleOfferedInPath = "//div[@id='SIvCob']";

        return $x(googleOfferedInPath).isDisplayed();
    }
}
