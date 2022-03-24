package com.softserveinc.ita;

import static com.codeborne.selenide.Condition.empty;
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

    public GoogleHomePage backToHomePageByLogo() {
        $x("//div[@class='logo']").click();

        return new GoogleHomePage();
    }
}
