package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleSearchResultPage {

    private final String inputFieldPath = "//input[@class='gLFyf gsfi']";

    public GoogleSearchResultPage search(String term) {
        $x(inputFieldPath).sendKeys(term);
        $x(inputFieldPath).sendKeys(ENTER);

        return this;
    }

    public GoogleSearchResultPage clearSearchField() {
        $x(inputFieldPath).clear();
        $x(inputFieldPath).shouldBe(empty);

        return this;
    }

    public String getTextFromLink(int numberOfLink) {
        String linkText = String.format("(//div[@class = 'g dFd2Tb']) [%s]", numberOfLink);

        return $x(linkText).text();
    }

    public GoogleSearchResultPage goToPage(int number) {
        String numberOfPage = String.format("//a[@aria-label = 'Page %s']", number);
        $x(numberOfPage).click();

        return this;
    }

    @Step("Verified presence of the google logo on the search results page")
    public boolean isGoogleLogoVisible() {
        String googleLogoXpath = "//*[@id = 'logo']";
        return $x(googleLogoXpath).shouldBe(visible).isDisplayed();
    }

    public boolean isNextLinkDisplayed() {

        return $x("(//a[@id='pnnext']/span)[2]").shouldBe(visible).isDisplayed();
    }

    public boolean isPreviousLinkDisplayed() {

        return $x("(//a[@id='pnprev']/span)[2]").shouldBe(visible).isDisplayed();
    }

    public boolean hasValidLink(int numberOfLink) {
        String linkAttribute = String.format("(//div[@class='g dFd2Tb'])[%d]/descendant::div[@class='ct3b9e']/a", numberOfLink);

        return $x(linkAttribute)
                .getAttribute("href")
                .contains("https://");
    }

}
