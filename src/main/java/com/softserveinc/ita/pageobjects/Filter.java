package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class Filter {
    @Step("choose {product} category in the filter")
    public SearchResultPage chooseOneOfCategory(String product) {
        String productLinkPath = String.format("//li[contains(@class, 'categories-filter__item')]//span[text() = '%s']", product);
        $x(productLinkPath)
                .click();

        return new SearchResultPage();
    }

    @Step("Filtered products by \"{filterCategoryName}\" Category and \"{filterCheckboxName}\" Checkbox")
    public SearchResultPage filterByCategory(String filterCategoryName, String filterCheckboxName) {
        String filterMinimizedCategoryPath = String.format("//div[@class='sidebar-block sidebar-block_state_collapsed ng-star-inserted']" +
                "//span[contains(text(),'%s')]", filterCategoryName);

        if ($x(filterMinimizedCategoryPath).is(exist)) {
            $x(filterMinimizedCategoryPath).click();
        }
        $x(String.format("//li//a[@data-id='%s']", filterCheckboxName)).click();

        return new SearchResultPage();
    }

    @Step("Filtered products by amount of price from {filterPriceMinNumber} to {filterPriceMaxNumber}")
    public SearchResultPage filterByPrice(String filterPriceMinNumber, String filterPriceMaxNumber) {
        String filterPriceFormPath = "//form[@class='slider-filter__form ng-untouched ng-pristine ng-valid']";

        if ($x(filterPriceFormPath).is(not(visible))) {
            $x(filterPriceFormPath).click();
        }

        String filterPriceMinPath = "//input[@formcontrolname='min']";
        $x(filterPriceMinPath).click();
        $x(filterPriceMinPath).clear();
        $x(filterPriceMinPath).setValue(filterPriceMinNumber);

        String filterPriceMaxPath = "//input[@formcontrolname='max']";
        $x(filterPriceMaxPath).click();
        $x(filterPriceMaxPath).clear();
        $x(filterPriceMaxPath).setValue(filterPriceMaxNumber).pressEnter();

        return new SearchResultPage();
    }
}
