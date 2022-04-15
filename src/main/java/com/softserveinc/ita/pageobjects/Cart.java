package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Cart {

    public InCartProduct getProduct(int index) {

        return new InCartProduct(String.format("(//div[@class='cart-product ng-star-inserted'])[%s]", index));
    }

    public int getTotalPrice() {
        $x("//div[@class='modal__content modal__content--locked']").shouldNotBe(visible);

        return Integer.parseInt($x("//div[@class='cart-receipt__sum-price']/span").text());
    }
}
