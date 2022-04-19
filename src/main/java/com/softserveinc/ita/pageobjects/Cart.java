package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Cart {

    public InCartProduct getProduct(int index) {

        return new InCartProduct(String.format("(//div[@class='cart-product ng-star-inserted'])[%s]", index));
    }

    public InCartProduct getProduct(String name) {

        return new InCartProduct(String.format("//a[@title='%s']/ancestor::div[@class='cart-product ng-star-inserted']", name));
    }

    public boolean isEmpty() {
        String cartHeadingPath = "//div[@data-testid='empty-cart']/h4";
        try {
            return $x(cartHeadingPath)
                    .shouldBe(visible)
                    .isDisplayed();
        } catch (AssertionError assertionError) {
            return false;
        }
    }

    public RecommendedProduct getRecommendedProduct(int elementNumber) {
        return new RecommendedProduct(elementNumber);
    }

    public void closePopup() {
        $x("//button[@class='modal__close']").click();
    }
}
