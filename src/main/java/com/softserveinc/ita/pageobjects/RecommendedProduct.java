package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class RecommendedProduct {

    private final String rootElementPath;

    @Step("Added product from recommended list to cart")
    public Cart addToCart() {
        $x(String.format("%s%s", rootElementPath,
                "//button[@class='buy-button lite-tile__buy-button ng-star-inserted']")).click();

        return new Cart();
    }
}
