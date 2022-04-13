package com.softserveinc.ita.pageobjects;

import static java.lang.String.format;

public class Cart {

    public InCartProduct getProduct(int index) {

        return new InCartProduct(String.format("(//div[@class='cart-product ng-star-inserted'])[%s]", index));
    }
}
