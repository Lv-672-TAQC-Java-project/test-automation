package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Cart {

    public InCartProduct getProduct(int index) {

        return new InCartProduct(String.format("(//div[@class='cart-product ng-star-inserted'])[%s]", index));
    }

    public boolean isCartEmpty() {
        String cartHeadingPath = "//div[@data-testid='empty-cart']/h4";

        return $x(cartHeadingPath).shouldBe(visible)
                .isDisplayed();
    }
}
