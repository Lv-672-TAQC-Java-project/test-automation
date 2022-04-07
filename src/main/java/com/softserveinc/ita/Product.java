package com.softserveinc.ita;

import com.codeborne.selenide.SelenideElement;

public class Product {

    private final SelenideElement rootElement;

    public Product(SelenideElement element) {
        this.rootElement = element;
    }

    public String getProductName() {

        return rootElement.$x(".//span[contains(@class, 'goods-tile__title')]").text();
    }
}
