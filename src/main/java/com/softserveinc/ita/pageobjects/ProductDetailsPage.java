package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.pageobjects.WebElementUtil.isDisplayed;
import static java.time.Duration.ofSeconds;

public class ProductDetailsPage {

    private final SelenideElement productNameLabel = $x("//div[@class='product__heading']/child::h1");

    public boolean isOpened() {
        return isDisplayed($x("//product-tab-main[@class='ng-star-inserted']"), ofSeconds(5));
    }

    public String getProductName() {
        return productNameLabel.getText();
    }

    @Step("Added product to cart")
    public Cart addToCart() {
        $x("//button[contains(@class,'buy-button button button--with-icon')]").click();

        return new Cart();
    }

    @Step("Scrolled to product name")
    public ProductDetailsPage scrollToProductName() {
        productNameLabel.scrollIntoView(true);

        return this;
    }
}
