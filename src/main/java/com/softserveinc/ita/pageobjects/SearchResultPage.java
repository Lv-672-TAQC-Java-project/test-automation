package com.softserveinc.ita.pageobjects;

import lombok.Getter;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;

@Getter
public class SearchResultPage {

    private Header header = new Header();

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
}
