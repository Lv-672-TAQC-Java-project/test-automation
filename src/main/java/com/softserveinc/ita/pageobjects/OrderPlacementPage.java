package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class OrderPlacementPage {

    @Step("Submitted an order")
    public OrderPlacementPage submitOrder() {
        $x("//div[@class='checkout-total__buttons']").click();

        return this;
    }

    public boolean isOpened() {
        try {
            return $x("//div[@class='central-wrapper']/h1")
                    .shouldBe(visible)
                    .isDisplayed();
        } catch (AssertionError assertionError) {

            return false;
        }
    }

    public boolean isFieldRequired(String fieldName) {
        return $x(format("//div[contains(@class, '%s')]", fieldName))
                .getAttribute("class")
                .contains("validation_type_error");
    }
}