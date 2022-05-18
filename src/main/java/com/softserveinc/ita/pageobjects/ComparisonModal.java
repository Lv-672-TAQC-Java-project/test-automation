package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ComparisonModal extends BasePage {

    @Step("Opened Comparison Page {category}")
    public ComparisonPage openComparisonPage(String category) {

        String categoryLinkPath = format("//*[contains(@class, 'comparison-modal__list')]//a[contains(text(), '%s')]", category);
        $x(categoryLinkPath).click();

        return new ComparisonPage();
    }
}
