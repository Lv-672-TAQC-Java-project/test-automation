package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.pageobjects.components.Header;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.softserveinc.ita.utils.PropertyUtil.getHomePageUrl;

@Getter
public class HomePage {

    private final CategorySideBar categorySideBar = new CategorySideBar();
    private final Header header = new Header();

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

    @Step("Emptied comparison modal and close popup")
    public HomePage emptyComparisonModal() {
        MenuModal menuModal = header.openMenuModal();
        //verify if comparison button is visible
        if ($$x("//ul[contains(@class, 'side-menu__list--top')]//li")
                .shouldHave(sizeNotEqual(0))
                .size() == 3) {
            menuModal
                    .openComparisonModal()
                    .removeAllItems()
                    .close();
        } else {
            menuModal.close();
        }

        return this;
    }
}
