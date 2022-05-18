package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class SubCategoryComparisonModal {
    private final String rootElementPath;

    @Step("Opened Comparison Page")
    public ComparisonPage openComparisonPage() {
        $x(rootElementPath + "//a").click();

        return new ComparisonPage();
    }

    @Step("Removed from comparison modal")
    public ComparisonModal remove() {
        //save the name of the current category
        String subCategoryName = $x(rootElementPath + "//a")
                .text()
                .replaceAll("\\s[0-9]", "");

        $x(rootElementPath + "//button").click();

        $x(format("%s//a[contains(text(), '%s')]", rootElementPath, subCategoryName)).shouldNotBe(exist, ofSeconds(20));

        return new ComparisonModal();
    }
}
