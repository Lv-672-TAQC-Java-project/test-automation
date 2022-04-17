package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProductDetailsPage {

    public boolean isProductDetailsPageDisplayed() {
        try {
            return $x("//product-tab-main[@class='ng-star-inserted']")
                    .shouldBe(visible)
                    .isDisplayed();
        } catch (AssertionError assertionError) {

            return false;
        }
    }

    public String getProductName() {
        return $x("//div[@class='product__heading']/child::h1[@class='product__title']").getText();
    }

    @Step("Added product to cart")
    public Cart addToCart() {
        $x("//button[contains(@class,'buy-button button button--with-icon')]").click();

        return new Cart();
    }
}

