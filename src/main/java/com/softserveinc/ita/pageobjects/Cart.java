package com.softserveinc.ita.pageobjects;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
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

    public boolean isOpened() {
        try {
            return $x("//div[@class='modal__header']")
                    .shouldBe(visible)
                    .isDisplayed();
        } catch (AssertionError assertionError) {
            return false;
        }
    }

    public List<InCartProduct> getInCartProducts() {
        List<InCartProduct> inCartProducts = new LinkedList<>();
        String inCartProductsPath = "//div[@class='cart-product ng-star-inserted']";
        int amountOfInCartProducts = $$x(inCartProductsPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(10)).size();

        for (int i = 1; i <= amountOfInCartProducts; i++) {
            inCartProducts.add(new InCartProduct(String.format("(%s)[%s]", inCartProductsPath, i)));
        }

        return inCartProducts;
    }
}
