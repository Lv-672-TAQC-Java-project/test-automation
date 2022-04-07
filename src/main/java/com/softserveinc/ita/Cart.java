package com.softserveinc.ita;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Cart {

    private String productInCartPath = "//ul[@class='cart-list ng-star-inserted']/li";

    public boolean isCartContainsAddedCountOfProduct(int countOfAddedProduct) {
        int numberOfProductInCart = $$x(productInCartPath)
                .shouldBe(sizeNotEqual(0), Duration.ofSeconds(20)).size();
        if (numberOfProductInCart == countOfAddedProduct){
            return true;
        }
        return false;
    }

    public Cart openCartProductActions(){
        $x("//button[@id='cartProductActions0']").click();

        return this;
    }

    public Cart removeOption(){
        $x("//button[contains(text(),'Видалити')]").click();

        return this;
    }

    public boolean isCartEmpty(){
        String cartHeadingPath = "//div[@data-testid='empty-cart']/h4";

        return $x(cartHeadingPath).shouldHave(text("Кошик порожній"))
                .shouldBe(visible)
                .isDisplayed();
    }
}
