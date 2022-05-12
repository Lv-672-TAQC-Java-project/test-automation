package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class FlawedProductsPage extends SearchResultPage {

    public SubCategoryPage openFlawedProductsCategoryPage(String productsCategoryName) {
        $x(format("//*[@class = 'portal-navigation__link-text' and contains(text(), '%s)]", productsCategoryName)).click();

        return new SubCategoryPage();
    }
}
