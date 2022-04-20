package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class RecommendedProduct {
    private String rootElementPath;

    RecommendedProduct(int recommendedProductNumber) {
        this.rootElementPath = String.format("//div[@class='modal__content']" +
                "//li[@class='simple-slider__item ng-star-inserted'][%d]", recommendedProductNumber);
    }

    @Step("Added product from recommended list to cart")
    public Cart addToCart() {
        $x(String.format("%s%s", rootElementPath,
                "//button[@class='buy-button lite-tile__buy-button ng-star-inserted']")).click();

        return new Cart();
    }
}
