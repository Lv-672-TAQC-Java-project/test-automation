package com.softserveinc.ita;

import static com.codeborne.selenide.Selenide.$x;

public class Cart {

    public String getFirstProductItemNameFromCart() {
        return $x("//div[@class='cart-product__main']/child::a[@class='cart-product__title']").getText();
    }
}
