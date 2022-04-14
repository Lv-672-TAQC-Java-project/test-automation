package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class InCartProduct {

    private final String rootElementPath;

    public String getName() {

        return $x(String.format("%s%s", rootElementPath,
                "//div[@class='cart-product__main']/a")).text();
    }

    public float getFirstServicePrice(){
        String price = $x(String.format("%s%s", rootElementPath,
                "//span[@class='cart-service__prices']/span[1]"))
                .text()
                .replace("₴", "")
                .replace(" ", "");

        return Float.parseFloat(price);
    }

    @Step("Selected first additional service")
    public Cart selectFirstService(){
        $x(String.format("%s%s", rootElementPath,
                "//rz-service-item/div/label")).click();

        return new Cart();
    }
}
