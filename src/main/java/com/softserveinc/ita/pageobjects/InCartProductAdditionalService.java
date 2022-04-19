package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class InCartProductAdditionalService {

    private final String rootElementPath;

    @Step("Selected additional service")
    public void select() {
        $x(rootElementPath).click();

        new Cart();
    }

    public int getPrice(){
        String price = $x(String.format("%s%s", rootElementPath, "//span[2]/span[1]"))
                .text()
                .replace("₴", "")
                .replace(" ", "");

        return Integer.parseInt(price);
    }
}