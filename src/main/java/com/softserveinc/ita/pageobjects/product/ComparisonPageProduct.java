package com.softserveinc.ita.pageobjects.product;

import lombok.AllArgsConstructor;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class ComparisonPageProduct {
    private final String rootElementPath;

    public String getProductName() {
        return $x(String.format("%s%s", rootElementPath, "//a[@class= 'product__heading']")).text();
    }

    public List<String> getCharacteristics() {
        String productName = getProductName();
        String characteristicsLocator = String.format("//*[contains(text(), '%s')]//following-sibling::dd", productName);
        return $$x(characteristicsLocator)
                .shouldHave(sizeGreaterThan(0))
                .texts();
    }
}
