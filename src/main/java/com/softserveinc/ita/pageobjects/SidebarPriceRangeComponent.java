package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class SidebarPriceRangeComponent {

    public SidebarPriceRangeComponent typeInMinimumPriceRange(String value) {
        String minimalPriceRangeFieldPath = "//*[@formcontrolname = 'min']";
        $x(minimalPriceRangeFieldPath).click();
        $x(minimalPriceRangeFieldPath).clear();
        $x(minimalPriceRangeFieldPath).sendKeys(value);
        return this;
    }

    public SidebarPriceRangeComponent typeInMaximumPriceRange(String value) {
        String maximalPriceRangeFieldPath = "//*[@formcontrolname = 'max']";
        $x(maximalPriceRangeFieldPath).click();
        $x(maximalPriceRangeFieldPath).clear();
        $x(maximalPriceRangeFieldPath).sendKeys(value);
        return this;
    }

    public SearchResultPage sortSearchResultsByPriceRange() {
        $x("//*[@type = 'submit']").click();
        return new SearchResultPage();
    }
}
