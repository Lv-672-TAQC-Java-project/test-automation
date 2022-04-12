package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ComparisonModal {

    @Step("Opened Comparison Page by category {category}")
    public ComparisonPage openComparisonPageByCategory(String category) {

        String categoryLinkPath = String.format("//*[contains(@class, 'comparison-modal__list')]//a[contains(text(), '%s')]", category);
        $x(categoryLinkPath).click();

        return new ComparisonPage();
    }
}
