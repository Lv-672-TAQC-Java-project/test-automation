package com.softserveinc.ita.pageobjects.components;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.FilterCategory;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class FilterCategorySideBar {
    private final String categoryPath = "//li[contains(@class, 'categories-filter__item')]//a[contains(@href, '%s')]";

    @Step("Expanded all categories list")
    public FilterCategorySideBar expandAllCategoriesList() {
        String allCategoriesButtonPath = "//li[contains(@class, 'categories-filter__toggle-main')]//button";
        $x(allCategoriesButtonPath).click();

        return this;
    }

    @Step("Filtered by {categoryName}")
    public SearchResultPage filterByCategory(FilterCategory categoryName) {
        $x(String.format(categoryPath, categoryName.getCategorySectionId())).click();

        return new SearchResultPage();
    }

    @Step("Expanded sub categories list in {categoryName}")
    public FilterCategorySideBar expandAllSubCategoriesList(FilterCategory categoryName) {
        String allSubCategoriesButtonPath = String.format(categoryPath + "//ancestor::li[contains(@class, 'categories-filter')]" +
                "//button", categoryName.getCategorySectionId());
        $x(allSubCategoriesButtonPath).click();

        return this;
    }

    @Step("Filtered by {subCategoryName}")
    public SearchResultPage filterBySubCategory(String subCategoryName) {
        // used \" to make the locator work correctly
        String subCategoryLinkPath = String.format("//li[contains(@class, 'categories-filter__item')]/ul//span[text() = \"%s\"]", subCategoryName);
        $x(subCategoryLinkPath).click();
        //in progress
        $x("//div[@class = 'rz-search-result-qnty']/a").shouldHave(Condition.text(subCategoryName), Duration.ofSeconds(10));

        return new SearchResultPage();
    }
}
