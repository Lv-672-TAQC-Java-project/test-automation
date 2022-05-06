package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.components.LastViewedProductsSection;
import io.qameta.allure.Step;
import lombok.Getter;

@Getter
public class HomePage {

    private final CategorySideBar categorySideBar = new CategorySideBar();
    private final Header header = new Header();
    private final LastViewedProductsSection lastViewedProductsSection = new LastViewedProductsSection();

    @Step("Opened home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");

        return this;
    }

    /**
     * Cart testing precondition.
     * Use this method to empty the cart before the test.
     */
    @Step("Emptied cart and close popup")
    public HomePage emptyCart() {
        header
                .openCart()
                .empty()
                .close();

        return this;
    }
}
