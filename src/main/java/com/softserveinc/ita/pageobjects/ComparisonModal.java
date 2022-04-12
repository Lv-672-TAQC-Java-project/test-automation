package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ComparisonModal {

    @Step("Opened Comparison Page {category}")
    public ComparisonPage openComparisonPage(String category) {

        String categoryLinkPath = String.format("//*[contains(@class, 'comparison-modal__list')]//a[contains(text(), '%s')]", category);
        $x(categoryLinkPath).click();

        return new ComparisonPage();
    }
}
