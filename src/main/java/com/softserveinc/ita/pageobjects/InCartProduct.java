package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class InCartProduct {

    private final String rootElementPath;

    public String getName() {

        return $x(String.format("%s%s", rootElementPath,
                "//div[@class='cart-product__main']/a")).text();
    }

    @Step("Opened the product actions menu in the cart")
    public InCartProduct openCartProductActions() {
        $x(String.format("%s%s", rootElementPath,
                "//rz-popup-menu/button")).click();

        return this;
    }

    @Step("Removed product from the cart")
    public Cart removeProductFromCart() {
        $x(String.format("%s%s", rootElementPath,
                "//rz-popup-menu//li[@class='popup-menu__item ng-star-inserted']//button")).click();

        return new Cart();
    }
}
