package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Filter {

    @Step("choose {product} category in the filter")
    public SearchResultPage chooseOneOfCategory(String product) {

        String productLinkPath = String.format("//li[contains(@class, 'categories-filter__item')]//span[text() = '%s']", product);
        $x(productLinkPath)
                .click();

        return new SearchResultPage();
    }
}
