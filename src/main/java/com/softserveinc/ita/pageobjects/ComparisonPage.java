package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ComparisonPage {

    private Header header = new Header();

    public ComparisonPageProduct getProduct(int index) {
        return new ComparisonPageProduct(
                String.format("//*[@class='products-grid__cell ng-star-inserted'][%s]", index));
    }
    private String productsPath = "//rz-products-section//li";

    @Step("add more products to the comparison")
    public SearchResultPage addMoreProduct() {
        $x("//a/span[@class = 'comparison-settings__label']")
                .click();

        return new SearchResultPage();
    }

    public int getSize(){
        return $$x(productsPath).shouldHave(sizeGreaterThan(0), Duration.ofSeconds(3)).size();
    }

    public boolean areProductsDisplayed() {
        boolean isProductDisplayed = false;
        for (int index = 0; index < getSize(); index++) {
            isProductDisplayed = $x(String.format("%s[%d]", productsPath, index + 1))
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .isDisplayed();
        }
        return isProductDisplayed;
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
