package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class AdditionalProductService {
    private String rootElementPath;

    public AdditionalProductService(String productName, int index) {
        String product = String.format("//a[@title=%s]/ancestor::div[@class='cart-product ng-star-inserted']", "\"" + productName + "\"");

        this.rootElementPath = String.format("%s%s", product, String.format("//li[%s]/rz-service-item", index));
    }

    @Step("Selected additional service")
    public Cart select() {
        $x(rootElementPath)
                .scrollIntoView(true)
                .click();

        return new Cart();
    }

    public int getPrice() {
        String price = $x(String.format("%s%s", rootElementPath, "//span[2]/span[1]"))
                .text()
                .replace("₴", "")
                .replace(" ", "");

        return Integer.parseInt(price);
    }
}