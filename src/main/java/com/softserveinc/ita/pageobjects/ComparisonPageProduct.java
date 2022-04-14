package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class ComparisonPageProduct {
    private final String rootElementPath;

    public String getProductName() {
        return $x(String.format("%s%s", rootElementPath,
                "//a[@class= 'product__heading']")).text();
    }
}
