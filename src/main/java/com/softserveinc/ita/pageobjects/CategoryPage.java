package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.models.PopularBrandName;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;
import static java.time.Duration.*;

public class CategoryPage {

    @Step("Opened sub category page")
    public SubCategoryPage openSubCategoryPage(String subCategoryName) {
        $x(format("//div[@class='tile-cats']//a[contains(text(),'%s')]", subCategoryName)).click();

        return new SubCategoryPage();
    }

    @Step("Opened {popularBrandName} products page")
    public SearchResultPage openPopularBrendProductsPage(PopularBrandName popularBrandName) {
        $x(String.format("//a[@title='%s']", popularBrandName.getPopularBrandName())).click();

        return new SearchResultPage();
    }
}
