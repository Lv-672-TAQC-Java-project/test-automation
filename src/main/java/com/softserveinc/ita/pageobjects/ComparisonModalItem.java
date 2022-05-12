package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
@AllArgsConstructor
public class ComparisonModalItem {
    private final String rootElementPath;

    @Step("Opened Comparison Page")
    public ComparisonPage openComparisonPage() {
        $x(rootElementPath + "//a").click();

        return new ComparisonPage();
    }

    @Step("Removed from comparison modal")
    public ComparisonModal remove(){
        $x(rootElementPath + "//button").click();

        return new ComparisonModal();
    }
}
