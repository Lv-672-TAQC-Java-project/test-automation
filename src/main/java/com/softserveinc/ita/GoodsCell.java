package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class GoodsCell {

    @Step("{amount} products to comparison")
    public GoodsCell buttonLibraInCatalog(int amountLibra) {
        for (int i = 0; i < amountLibra; i++) {
            sleep(1000);
            $$x("//li[@class='catalog-grid__cell catalog-grid__cell_type_slim ng-star-inserted']" +
                    "//button[@class='compare-button ng-star-inserted']").get(i).click();
        }

        return this;
    }

    public GoodsCell buttonLibraInHead() {
        sleep(1000);
        $x("//li[@class='header-actions__item header-actions__item--comparison']//button").click();

        return this;
    }

    @Step("go to comparison modal")
    public ComparisonModal buttonToComparison() {
        $x("//li[@class='comparison-modal__item ng-star-inserted']" +
                "/a[@class='comparison-modal__link']").click();

        return new ComparisonModal();
    }

}
