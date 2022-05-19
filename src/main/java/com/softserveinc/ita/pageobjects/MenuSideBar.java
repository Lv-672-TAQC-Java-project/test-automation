package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MenuSideBar {

    @Step("Opened comparison modal")
    public ComparisonModal openComparisonModal() {
        $x("//ul[contains(@class, 'side-menu__list--top')]//li[3]").click();

        return new ComparisonModal();
    }

    @Step("Closed menu modal")
    public HomePage close() {
        $x("//button[contains(@class, 'side-menu__close')]").click();

        return new HomePage();
    }

    public boolean areThereProductsInComparison() {
        //Comparison modal button has index 3 and be visible if it is filled
        return $$x("//ul[contains(@class, 'side-menu__list--top')]//button")
                .shouldHave(sizeNotEqual(0))
                .size() == 3;
    }
}
