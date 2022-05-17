package com.softserveinc.ita.pageobjects.product;

import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class InOrderProduct {

    private final String rootElementPath;

    public String getName() {

        return $x(rootElementPath + "//div[@class = 'checkout-product__title-product']").text();
    }

    public int getPrice() {
        var price = $x(rootElementPath + "//span[contains(@class, 'checkout-product__price')][1]")
                .text()
                .replace("₴", "")
                .replace(" ", "");

        return Integer.parseInt(price);
    }
}
