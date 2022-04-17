package com.softserveinc.ita.pageobjects;

import lombok.Getter;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;

@Getter
public class ComparisonPage {

    private Header header = new Header();

    public ComparisonPageProduct getProduct(int index) {
        return new ComparisonPageProduct(
                String.format("//*[@class='products-grid__cell ng-star-inserted'][%s]", index));
    }

    public ComparisonPageProduct getProduct(String name) {
        return new ComparisonPageProduct(
                String.format("//a[normalize-space(text()) = '%s']//ancestor::li", name));
    }

    public List<ComparisonPageProduct> getAllComparisonPageProducts() {
        List<ComparisonPageProduct> comparisonPageProducts = new LinkedList<>();
        String productsPath = "//*[@class='products-grid__cell ng-star-inserted']";
        int amountOfProducts = $$x(productsPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(10)).size();

        for (int i = 1; i <= amountOfProducts; i++) {
            comparisonPageProducts.add(new ComparisonPageProduct(String.format("(%s)[%s]", productsPath, i)));
        }
        return comparisonPageProducts;
    }
}
