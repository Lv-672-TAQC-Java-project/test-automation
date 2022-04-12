package com.softserveinc.ita;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Cart {

    public Cart openProductActionsInCart(String productName){
        $x(String.format("//a[@title='%s']/parent::div/following-sibling::rz-popup-menu/button",productName)).click();

        return this;
    }

    public Cart removeProductItemFromCart(){
        $x("//li[@class='popup-menu__item ng-star-inserted']//button").click();

        return this;
    }

    public boolean isCartEmpty(){
        String cartHeadingPath = "//div[@data-testid='empty-cart']/h4";

        return $x(cartHeadingPath).shouldBe(visible)
                .isDisplayed();
    }
}
