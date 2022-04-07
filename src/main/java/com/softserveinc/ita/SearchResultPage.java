package com.softserveinc.ita;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {

    private Header header = new Header();

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        String productsPath = "//div[@class='goods-tile__inner']";
        int amountOfProducts = $$x(productsPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(5)).size();

        for (int i = 1; i <= amountOfProducts; i++) {
            products.add(new Product($x(String.format("(%s)[%s]", productsPath, i))));
        }

        return products;
    }
}
