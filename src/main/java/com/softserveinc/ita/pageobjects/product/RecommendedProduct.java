package com.softserveinc.ita.pageobjects.product;

import com.softserveinc.ita.pageobjects.Cart;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class RecommendedProduct {
    private final String rootElementPath;

    public RecommendedProduct(int recommendedProductNumber) {
        this.rootElementPath = String.format("//li[@class='simple-slider__item ng-star-inserted'][%d]",
                recommendedProductNumber);
    }

    @Step("Added product from recommended list to cart")
    public Cart addToCart() {
        $x(String.format("%s%s", rootElementPath,
                "//button[@class='buy-button lite-tile__buy-button ng-star-inserted']")).click();

        return new Cart();
    }

    public int getPrice() {
        String price = $x(String.format("%s%s", rootElementPath, "//span[@class = 'lite-tile__price-value']"))
                .text()
                .replace(" ", "");

        return Integer.parseInt(price);
    }
}
