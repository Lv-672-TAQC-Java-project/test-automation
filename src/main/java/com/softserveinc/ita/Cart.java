package com.softserveinc.ita;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Cart {

    public boolean isCartModalDisplayed() {
        return $x("//div[@class='modal__header']")
                .shouldBe(visible, Duration.ofSeconds(10))
                .isDisplayed();
    }

    public String getProductItemNameFromCart() {
        return $x("//div[@class='cart-product__main']/child::a[@class='cart-product__title']").getText();
    }
}