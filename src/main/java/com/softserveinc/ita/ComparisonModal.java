package com.softserveinc.ita;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ComparisonModal {

    public ComparisonPage chooseProductFromComparisonList(String product) {

        String productLinkPath = "//a[@class = 'comparison-modal__link']";

        int size = $$x(productLinkPath).size();

        for (int index = 0; index < size; index++) {
            SelenideElement currentProductXPath = $x(String.format("(%s)[%d]", productLinkPath, index + 1));
            String currentProduct = currentProductXPath.text();
            if (currentProduct.contains(product)) {
                currentProductXPath.click();
            }
        }

        return new ComparisonPage();
    }
}
