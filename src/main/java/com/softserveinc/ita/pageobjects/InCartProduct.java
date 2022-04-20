package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class InCartProduct {

    private final String rootElementPath;
    final String additionalServicePath = "//li[%s]/rz-service-item";

    public String getName() {

        return $x(String.format("%s%s", rootElementPath, "//div[@class='cart-product__main']/a")).text();
    }

    @Step("Removed product from the cart")
    public Cart remove() {
        $x(String.format("%s%s", rootElementPath, "//rz-popup-menu/button")).click();
        $x("//li[@class='popup-menu__item ng-star-inserted']//button").click();

        return new Cart();
    }

    public ProductAdditionalService getAdditionalProductService(int index) {

        return new ProductAdditionalService(String.format("%s%s", rootElementPath, String.format(additionalServicePath, index)));
    }
}
