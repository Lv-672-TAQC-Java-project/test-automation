package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.models.BrandName;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CategoryPage {

    @Step("Opened sub category page")
    public SubCategoryPage openSubCategoryPage(String subCategoryName) {
        $x(format("//div[@class='tile-cats']//a[contains(text(),'%s')]", subCategoryName)).click();

        return new SubCategoryPage();
    }

    @Step("Opened {brandName} products page")
    public SearchResultPage openBrandProductsPage(BrandName brandName) {
        $x(String.format("//a[@title='%s']", brandName.getName())).click();

        return new SearchResultPage();
    }
}
