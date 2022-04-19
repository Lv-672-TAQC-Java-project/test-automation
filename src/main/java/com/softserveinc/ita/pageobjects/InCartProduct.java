package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class InCartProduct {

    private final String rootElementPath;

    public String getName() {

        return $x(String.format("%s%s", rootElementPath, "//div[@class='cart-product__main']/a")).text();
    }

    @Step("Removed product from the cart")
    public Cart remove() {
        $x(String.format("%s%s", rootElementPath, "//rz-popup-menu/button")).click();
        $x("//li[@class='popup-menu__item ng-star-inserted']//button").click();

        return new Cart();
    }

    @Step("Add one more product from the cart")
    public Cart addOneMoreProduct() {
        $x(String.format("%s%s", rootElementPath,
                "//button[@data-testid='cart-counter-increment-button']")).click();

        return new Cart();
    }
}
