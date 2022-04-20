package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import lombok.Getter;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class SearchResultPage {

    private Header header = new Header();
    private Filter filter = new Filter();

    public List<Product> getProducts() {
        List<Product> products = new LinkedList<>();
        String productsPath = "//div[@class='goods-tile__inner']";
        int amountOfProducts = $$x(productsPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(10)).size();

        for (int i = 1; i <= amountOfProducts; i++) {
            products.add(new Product(String.format("(%s)[%s]", productsPath, i)));
        }

        return products;
    }

    public Product getProduct(int index) {

        return new Product(String.format("(//div[@class='goods-tile__inner'])[%s]", index));
    }

    @Step("added products to comparison")
    public Header addProductsToComparison(String[] productsNames) {
        for (String productName: productsNames) {
            getProduct(productName).addToListOfComparisons();
        }

        return new Header();
    }

    public Product getProduct(String name) {

        return new Product(String.format("//span[contains(text(),'%s')]/ancestor::div[@class='goods-tile__inner']", name));
    }

    public String getSearchTermLabel() {
        return $x("//div[@class='search-header ng-star-inserted']/h1").getText();
    }
}
