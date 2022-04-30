package com.softserveinc.ita.pageobjects.product;

import com.softserveinc.ita.pageobjects.Cart;
import com.softserveinc.ita.utils.WebElementUtil;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.time.Duration.ofSeconds;

public class RecommendedProduct {
    private final String rootElementPath;

    public RecommendedProduct(int recommendedProductNumber) {
        this.rootElementPath = String.format("//li[@class='simple-slider__item ng-star-inserted'][%d]",
                recommendedProductNumber);
    }

    @Step("Added product from recommended list to cart")
    public Cart addToCart() {
        $x(String.format("%s//button[@class='buy-button " +
                        "lite-tile__buy-button ng-star-inserted']", rootElementPath)).click();

        return new Cart();
    }

    public int getPrice() {
        isDisplayed($x("//rz-cart-accessories//div[@class='simple-slider']"), ofSeconds(5));

        String price = $x(String.format("%s//span[@class = 'lite-tile__price-value']", rootElementPath))
                .text()
                .replace(" ", "");

        return Integer.parseInt(price);
    }
}
