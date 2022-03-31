package com.softserveinc.ita;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleSearchResultPage {

    private final String inputFieldPath = "//input[@class='gLFyf gsfi']";

    @Step("Searched for {term}")
    public GoogleSearchResultPage search(String term) {
        $x(inputFieldPath).sendKeys(term);
        $x(inputFieldPath).sendKeys(ENTER);

        return this;
    }

    @Step("Cleared search field")
    public GoogleSearchResultPage clearSearchField() {
        $x(inputFieldPath).clear();
        $x(inputFieldPath).shouldBe(empty);

        return this;
    }

    public String getTextFromLink(int numberOfLink) {
        String linkText = String.format("(//div[@class = 'g dFd2Tb']) [%s]", numberOfLink);

        return $x(linkText).text();
    }

    @Step("Сheck if the next link is displayed")
    public boolean isNextLinkDisplayed() {
        String nextLinkPath = "//*[@id='pnnext']/span[2]";

        return $x(nextLinkPath)
                .shouldBe(visible, Duration.ofSeconds(10))
                .isDisplayed();
    }

    @Step("Go to page {number}")
    public GoogleSearchResultPage goToPage(int number) {
        String numberOfPage = String.format("//a[@aria-label = 'Page %s']", number);
        $x(numberOfPage).click();

        return this;
    }

    public boolean isPreviousLinkDisplayed() {

        return $x("(//a[@id='pnprev']/span)[2]").shouldBe(visible).isDisplayed();
    }

    @Step("Verify that {numberOfLink} link contains valid URL")
    public boolean hasValidLink(int numberOfLink) {
        String linkAttribute = String.format("(//div[@class='g dFd2Tb'])[%d]/descendant::div[@class='ct3b9e']/a", numberOfLink);

        return $x(linkAttribute)
                .getAttribute("href")
                .contains("https://");
    }
}
