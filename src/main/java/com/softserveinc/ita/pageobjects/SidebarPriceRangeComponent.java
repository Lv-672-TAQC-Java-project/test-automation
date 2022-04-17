package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class SidebarPriceRangeComponent {
    private final String rootElementPath;

    @Step("Typed in {value} to set price range minimum")
    public SidebarPriceRangeComponent typeInMinimumPriceRange(String value) {
        String minimalPriceRangeFieldPath = String.format("%s%s", rootElementPath, "//*[@formcontrolname = 'min']");
        $x(minimalPriceRangeFieldPath).click();
        $x(minimalPriceRangeFieldPath).clear();
        $x(minimalPriceRangeFieldPath).sendKeys(value);
        return this;
    }

    @Step("Typed in {value} to set price range maximum")
    public SidebarPriceRangeComponent typeInMaximumPriceRange(String value) {
        String maximalPriceRangeFieldPath = String.format("%s%s", rootElementPath, "//*[@formcontrolname = 'max']");
        $x(maximalPriceRangeFieldPath).click();
        $x(maximalPriceRangeFieldPath).clear();
        $x(maximalPriceRangeFieldPath).sendKeys(value);
        return this;
    }

    @Step("Sorted the results by custom price range")
    public SearchResultPage sortSearchResultsByPriceRange() {
        $x(String.format("%s%s", rootElementPath, "//*[@type = 'submit']")).click();
        return new SearchResultPage();
    }
}
