package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class FlawedProductsPage extends SearchResultPage {

    @Step("Opened flawed {productsCategoryName} products page")
    public SubCategoryPage openFlawedProductsCategoryPage(String productsCategoryName) {
        var categoryLocator = format("//*[@class = 'portal-navigation__link-text' and contains(text(), '%s')]", productsCategoryName);
        $x(categoryLocator)
                .hover()
                .click();

        return new SubCategoryPage();
    }
}
