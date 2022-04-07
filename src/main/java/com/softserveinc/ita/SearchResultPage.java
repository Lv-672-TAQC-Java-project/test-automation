package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {

    private Header header = new Header();

    public String getSearchedProductItemName() {
        return $x("//div[contains(text(),'Знайдено більше 1000 товарів')]/parent::div/h1").getText();
    }

    public String getFirstProductItemName() {
        return $x("(//div[@class='goods-tile ng-star-inserted'])[1]/descendant::span[@class='goods-tile__title']").getText();
    }

    @Step("Added first product item to the cart")
    public SearchResultPage addFirstProductItemToCart() {
        $x("(//button[@class='buy-button goods-tile__buy-button ng-star-inserted'])[1]").click();

        return this;
    }
}
