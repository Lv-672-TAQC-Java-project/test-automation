package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

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

    @Step("Opened additional services")
    public Cart openAdditionalServices() {
        SelenideElement additionalServicesButton = $x(String.format("%s%s", rootElementPath,
                "//button[contains(@class, 'cart-services__toggle')]"));
        SelenideElement hiddenAdditionalServicesList = $x(String.format("%s%s", rootElementPath,
                "//ul[@Class='cart-services__list display-none']"));

        if (hiddenAdditionalServicesList.exists()) {
            additionalServicesButton.click();
        }

        return new Cart();
    }

    public AdditionalProductService getAdditionalProductService(int index) {

        return new AdditionalProductService(index, rootElementPath);
    }
}
