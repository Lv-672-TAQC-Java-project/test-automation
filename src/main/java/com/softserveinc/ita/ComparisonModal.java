package com.softserveinc.ita;

import static com.codeborne.selenide.Selenide.$x;

public class ComparisonModal {

    public ComparisonPage openComparisonPage() {
        String locator = "//a[@class = 'comparison-modal__link']";
        $x(locator).click();
        return new ComparisonPage();
    }
}
