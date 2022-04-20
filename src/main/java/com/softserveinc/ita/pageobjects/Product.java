package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class Product {

    private final String rootElementPath;

    public String getName() {

        return $x(String.format("%s%s", rootElementPath,
                "//span[@class = 'goods-tile__title']")).text();
    }

    public int getPrice() {
        String price = $x(String.format("%s%s", rootElementPath, "//span[@class = 'goods-tile__price-value']"))
                .text()
                .replace(" ", "");

        return Integer.parseInt(price);
    }

    @Step("Added product to cart")
    public SearchResultPage addToCart() {
        $x(String.format("%s%s", rootElementPath,
                "//button[contains(@class, 'goods-tile__buy-button')]")).click();

        return new SearchResultPage();
    }

    @Step("Added product to list of comparisons")
    public SearchResultPage addToListOfComparisons() {
        $x(String.format("%s%s", rootElementPath,
                "//button[contains(@class, 'compare-button')]")).click();

        return new SearchResultPage();
    }
}
