package com.softserveinc.ita;

import static com.codeborne.selenide.Selenide.$x;

public class Product {

    private final String rootElementPath;

    public Product(String element) {
        this.rootElementPath = element;
    }

    public String getProductName() {

        return $x(String.format("(%s)[%s]", rootElementPath,".//span[contains(@class, 'goods-tile__title')]"))
                .text();
    }
}
