package com.softserveinc.ita.pageobjects;

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
        String charsLocator = String.format("//*[contains(text(), '%s')]//following-sibling::dd", productName);
        return $$x(charsLocator).shouldHave(sizeGreaterThan(0)).texts();
    }
//    Not Working
//    public List<String> getCharacteristics() {
//        String charsLocator = String.format("%s%s", rootElementPath, "//following::ul //*[contains(text(), '%s')]");
//        return $$x(charsLocator).shouldHave(sizeGreaterThan(0)).texts();
//    }
}
