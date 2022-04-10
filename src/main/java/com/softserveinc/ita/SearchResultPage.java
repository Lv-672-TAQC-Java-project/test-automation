package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchResultPage {

    private Header header = new Header();

    @Step("add {amount} products to comparison")
    public Header addAmountProductsToComparison(int amount) {
        for (int i = 0; i < amount; i++) {
            $$x("//button[@class='compare-button ng-star-inserted']")
                    .get(i).click();
        }

        return new Header();
    }
}
