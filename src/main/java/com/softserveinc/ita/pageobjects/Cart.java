package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class Cart {

    public InCartProduct getProduct(int index) {

        return new InCartProduct(format("(//div[@class='cart-product ng-star-inserted'])[%s]", index));
    }

    public String getProductName(int productNumber) {
        return $x(format("(//div[@class='cart-product__main']/child::a[@class='cart-product__title'])[%d]", productNumber)).getText();
    }
}
