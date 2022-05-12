package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CategoryPage extends SearchResultPage {

    @Step("Opened sub category page")
    public SubCategoryPage openSubCategoryPage(String subCategoryName) {
        $x(format("//div[@class='tile-cats']//a[contains(text(),'%s')]", subCategoryName)).click();

        return new SubCategoryPage();
    }

    public SubCategoryPage openFlawedProductsCategoryPage(String productsCategoryName) {
        $x(format("//*[@class = 'portal-navigation__link-text' and contains(text(), '%s)]", productsCategoryName)).click();

        return new SubCategoryPage();
    }
}
