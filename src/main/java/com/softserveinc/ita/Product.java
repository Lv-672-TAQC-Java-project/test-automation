package com.softserveinc.ita;

import static com.codeborne.selenide.Selenide.$x;

public class Product {

    private final String rootElementPath;

    public Product(String rootElementPath) {
        this.rootElementPath = rootElementPath;
    }

    public String getProductName() {

        return $x(String.format("%s%s", rootElementPath,
                "//span[@class = 'goods-tile__title']")).text();
    }

    public String getPriceProduct() {

        return $x(String.format("%s%s", rootElementPath,
                "//span[@class = 'goods-tile__price-value']")).text();
    }

    public SearchResultPage addToCartProduct() {
        $x(String.format("%s%s", rootElementPath,
                "//button[contains(@class, 'goods-tile__buy-button')]")).click();

        return new SearchResultPage();
    }

    public SearchResultPage addProductToListOfComparisons() {
        $x(String.format("%s%s", rootElementPath,
                "//button[contains(@class, 'compare-button')]")).click();

        return new SearchResultPage();
    }
}

