package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.visible;
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

        return $x(googleLogoPath).shouldBe(visible).isDisplayed();
    }

    public boolean isSearchInputFieldDisplayed() {
        String searchInputFieldPath = "//div[@class='RNNXgb']";

        return $x(searchInputFieldPath).shouldBe(visible).isDisplayed();
    }

    public boolean isButtonGoogleSearchDisplayed() {
        String buttonGoogleSearchPath = "(//input[@class='gNO89b'])[2]";

        return $x(buttonGoogleSearchPath).shouldBe(visible).isDisplayed();
    }

    public boolean isButtonImFeelingLuckyDisplayed() {
        String buttonImFeelingLuckyPath = "(//input[@class='RNmpXc'])[2]";

        return $x(buttonImFeelingLuckyPath).shouldBe(visible).isDisplayed();
    }

    public boolean isGoogleLanguageFieldDisplayed() {
        String googleLanguageFieldPath = "//div[@id='SIvCob']";

        return $x(googleLanguageFieldPath).shouldBe(visible).isDisplayed();
    }
}
