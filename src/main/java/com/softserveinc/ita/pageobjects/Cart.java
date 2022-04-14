package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class Cart {

    public InCartProduct getProduct(int index) {

        return new InCartProduct(String.format("(//div[@class='cart-product ng-star-inserted'])[%s]", index));
    }

    public boolean isOpened() {
        return $x("//div[@class='modal__header']")
                .shouldBe(visible.because("Cart should be displayed"))
                .isDisplayed();
    }
}
