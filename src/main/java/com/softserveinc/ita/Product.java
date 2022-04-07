package com.softserveinc.ita;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class Product {

    private String productPath;

    public Product(String productPath) {
        this.productPath = productPath;
    }

    public SearchResultPage addProductToComparisonList() {
        String comparisonButtonPath = String.format("%s//button[contains(@class, 'compare-button')]", productPath);
        $x(comparisonButtonPath)
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();

        return new SearchResultPage();
    }
}
