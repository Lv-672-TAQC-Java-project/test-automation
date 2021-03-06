package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CategoryPage extends BasePage {

    @Step("Opened sub category page")
    public SubCategoryPage openSubCategoryPage(String subCategoryName) {
        $x(format("//div[@class='tile-cats']//a[contains(text(),'%s')]", subCategoryName)).click();

        return new SubCategoryPage();
    }

    @Step("Opened '{popularBrandName}' products page")
    public SearchResultPage openPopularBrandProductsPage(String popularBrandName) {
        $x(String.format("//a[@title='%s']", popularBrandName)).click();

        return new SearchResultPage();
    }
}
