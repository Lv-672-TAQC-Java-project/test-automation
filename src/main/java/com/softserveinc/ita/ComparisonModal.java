package com.softserveinc.ita;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class ComparisonModal {

    @Step("Last product displayed")
    public boolean isLastProductDisplayed() {

        return $$x("//a[@class='product__heading']").last()
                .shouldBe(visible, ofSeconds(3)).isDisplayed();

    }

    @Step("amount added of products to the comparison")
    public int checkAmountAddedProducts() {

        return $$x("//a[@class='product__heading']")
                .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(3)).size();
    }

    @Step("amount deleted products from comparison")
    public ComparisonModal deleteAmountProducts(int amount) {
        for (int i = 0; i < amount; i++) {
            $$x("//button[@class='button button--white button--small popup-menu__toggle popup-menu__toggle--context']")
                    .get(i).click();
            $x("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']")
                    .click();
        }

        return this;
    }

}
