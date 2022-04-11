package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ComparisonModal {

    @Step("Opened comparison page")
    public ComparisonPage openComparisonPage() {
        $x("//a[@class = 'comparison-modal__link']").click();
        return new ComparisonPage();
    }
}
