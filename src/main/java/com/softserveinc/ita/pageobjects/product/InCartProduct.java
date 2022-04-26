package com.softserveinc.ita.pageobjects.product;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.pageobjects.Cart;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class InCartProduct {

    private final String rootElementPath;

    public String getName() {

        return $x(String.format("%s%s", rootElementPath, "//div[@class='cart-product__main']/a")).text();
    }

    @Step("Removed product from the cart")
    public Cart remove() {
        $x(String.format("%s%s", rootElementPath, "//rz-popup-menu/button")).click();
        $x("//li[@class='popup-menu__item ng-star-inserted']//button").click();

        return new Cart();
    }

    @Step("Add one more product from the cart")
    public Cart addOneMoreProduct() {
        $x(String.format("%s//button[@data-testid='cart-counter-increment-button']", rootElementPath)).click();
        SelenideElement loadSpinner = $x("//div[@class='modal__content modal__content--locked']");
        Duration tenSeconds = ofSeconds(10);

        //sometimes page opens instead of popup
        if (isDisplayed(loadSpinner, tenSeconds)) {
            loadSpinner.shouldNotBe(visible, tenSeconds);
        }

        return new Cart();
    }
}
