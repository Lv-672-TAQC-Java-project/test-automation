package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.FilterSectionName;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class Filter {
    @Step("Filtered products by '{filterCheckboxName}' Checkbox")
    public SearchResultPage filterBySection(FilterSectionName filterSectionName, String filterCheckboxName) {
        String filterMinimizedSectionPath = String.format("//div[@class='sidebar-block sidebar-block_state_collapsed " +
                "ng-star-inserted'][@data-filter-name='%s']", filterSectionName.getFilterSectionPath());

        if ($x(filterMinimizedSectionPath).is(exist)) {
            $x(filterMinimizedSectionPath).click();
        }

        $x(String.format("//div[@data-filter-name='%s']//a[@data-id='%s']", filterSectionName.getFilterSectionPath(),
                filterCheckboxName)).click();

        $x(String.format("//a[@class='catalog-selection__link' and contains(text(), '%s')]",
                filterCheckboxName)).shouldBe(visible, ofSeconds(10));

        return new SearchResultPage();
    }

    @Step("Filtered products by amount of price from {filterPriceMinNumber} to {filterPriceMaxNumber}")
    public SearchResultPage filterByPrice(int filterPriceMinNumber, int filterPriceMaxNumber) {
        String filterPriceFormPath = "//form[@class='slider-filter__form ng-untouched ng-pristine ng-valid']";

        if ($x(filterPriceFormPath).is(not(visible))) {
            $x(filterPriceFormPath).click();
        }

        String filterPriceMinPath = "//input[@formcontrolname='min']";
        $x(filterPriceMinPath).click();
        $x(filterPriceMinPath).clear();
        $x(filterPriceMinPath).setValue(String.valueOf(filterPriceMinNumber));

        String filterPriceMaxPath = "//input[@formcontrolname='max']";
        $x(filterPriceMaxPath).click();
        $x(filterPriceMaxPath).clear();
        $x(filterPriceMaxPath).setValue(String.valueOf(filterPriceMaxNumber)).pressEnter();

        return new SearchResultPage();
    }

    public int getProductsAmountInFilterCheckbox(FilterSectionName filterSectionName, String filterCheckboxName) {
        String productsAmount = $x(String.format("//div[@data-filter-name='%s']//a[@data-id='%s']/span",
                filterSectionName.getFilterSectionPath(), filterCheckboxName))
                .text()
                .replaceAll("\\D+", "");

        return Integer.parseInt(productsAmount);
    }
}
