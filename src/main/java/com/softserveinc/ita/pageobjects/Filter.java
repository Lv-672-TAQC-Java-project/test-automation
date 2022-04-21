package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class Filter {

    private String categoryLinkPath = "//li[contains(@class, 'categories-filter__item')]//span[text() = \"%s\"]";

    @Step("Expanded all categories list")
    public Filter expandAllCategoriesList() {
        String allCategoriesButtonPath = "//li[contains(@class, 'categories-filter__toggle-main')]//button";
        $x(allCategoriesButtonPath).click();

        return new Filter();
    }

    @Step("Filtered by {categoryName}")
    public SearchResultPage filterByCategoryLink(String categoryName) {
        $x(String.format(categoryLinkPath, categoryName)).click();

        return new SearchResultPage();
    }

    @Step("Expanded sub categories list in {categoryName}")
    public Filter expandAllSubCategoriesList(String categoryName) {
        String allSubCategoriesButtonPath = String.format(categoryLinkPath + "//ancestor::li[contains(@class, 'categories-filter')]//button", categoryName);
        $x(allSubCategoriesButtonPath).click();

        return new Filter();
    }

    @Step("Filtered by {subCategoryName}")
    public SearchResultPage filterBySubCategoryLink(String subCategoryName) {
        String subCategoryLinkPath = String.format("//li[contains(@class, 'categories-filter__item')]/ul//span[text() = \"%s\"]", subCategoryName);
        $x(subCategoryLinkPath).click();

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
