package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.models.FilterCategory;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class FilterCategorySideBar {
    private String categoryPath = "//li[contains(@class, 'categories-filter__item')]//a[contains(@href, '%s')]";

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

        return new SearchResultPage();
    }
}
