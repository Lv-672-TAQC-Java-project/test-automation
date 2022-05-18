package com.softserveinc.ita.pageobjects.components;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.FilterCategory;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

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
        $x(format(categoryPath, categoryName.getCategorySectionId())).click();

        return new SearchResultPage();
    }

    @Step("Expanded sub categories list in {categoryName}")
    public FilterCategorySideBar expandAllSubCategoriesList(FilterCategory categoryName) {
        String allSubCategoriesButtonPath = format(categoryPath + "//ancestor::li[contains(@class, 'categories-filter')]" +
                "//button", categoryName.getCategorySectionId());
        $x(allSubCategoriesButtonPath).click();

        return this;
    }

    @Step("Filtered by {subCategoryName}")
    public SearchResultPage filterBySubCategory(String subCategoryName) {
        // used \" to make the locator work correctly
        String subCategoryLinkPath = format("//li[contains(@class, 'categories-filter__item')]/ul//span[text() = \"%s\"]", subCategoryName);
        $x(subCategoryLinkPath).click();

        $x(format("//li[contains(@class, 'catalog-selection__item')][2]" +
                "/a[contains(text(), '%s')]", subCategoryName)).shouldBe(visible, ofSeconds(10));

        return new SearchResultPage();
    }
}
