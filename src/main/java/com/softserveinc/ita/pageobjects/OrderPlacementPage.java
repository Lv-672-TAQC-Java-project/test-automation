package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class OrderPlacementPage extends BasePage {

    @Step("Submitted an order")
    public OrderPlacementPage submitOrder() {
        $x("//div[@class='checkout-total__buttons']").click();

        return this;
    }

    public boolean isOpened() {
        return isDisplayed($x("//div[@class='central-wrapper']/h1"), ofSeconds(5));
    }

    public boolean isFieldRequired(String fieldName) {
        return $x(format("//div[contains(@class, '%s')]", fieldName))
                .getAttribute("class")
                .contains("validation_type_error");
    }
}