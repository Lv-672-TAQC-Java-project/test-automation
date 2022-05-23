package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.components.LastViewedProductsSection;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.softserveinc.ita.utils.PropertyUtil.getHomePageUrl;

@Getter
public class HomePage {

    private final CategorySideBar categorySideBar = new CategorySideBar();
    private final Header header = new Header();
    private final LastViewedProductsSection lastViewedProductsSection = new LastViewedProductsSection();

    @Step("Opened home page")
    public HomePage open() {
        Selenide.open(getHomePageUrl());

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

    /**
     * Comparison modal testing precondition.
     * Use this method before the test to open menu
     * and remove all elements from comparison modal if it is filled
     * or close menu if comparison modal is not filled.
     */
    @Step("Emptied comparison modal and close popup")
    public HomePage emptyComparisonModal() {
        var menuSideBar = header.openMenuSideBar();
        if (menuSideBar.areThereProductsInComparison()) {
            menuSideBar
                    .openComparisonModal()
                    .removeAllSubCategories()
                    .close();
        } else {
            menuSideBar.close();
        }

        return this;
    }
}
