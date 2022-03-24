package com.softserveinc.ita;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleGoToPage {

    public GoogleGoToPage goToPage(int number) {
        String numberOfPage = String.format("//a[@aria-label = 'Page %s']", number);
        $x(numberOfPage).click();

        return new GoogleGoToPage();
    }

    public GoogleGoToPage getPreviousLink() {
        $x("(//a[@id='pnprev']/span)[2]").shouldBe(visible);

        return this;
    }
}
