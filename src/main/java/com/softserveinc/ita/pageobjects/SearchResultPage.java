package com.softserveinc.ita.pageobjects;

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
    //private ProductsSidebar productsSidebar = new ProductsSidebar();

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

    public String getSearchTermLabel() {
        return $x("//div[@class='search-header ng-star-inserted']/h1").getText();
    }

    public List<Integer> getAllProductsPrices(List<Product> list) {
        List<Integer> productsPrices = new LinkedList<>();
        int amountOfPrices = list.size();
        int i = 0;
        while (i != amountOfPrices) {
            i++;
            productsPrices.add(Integer.valueOf(getProduct(i).getPrice().replaceAll(" ", "")));

        }
        return productsPrices;
    }

    public SearchResultSidebar getSearchResultSidebar() {
        return new SearchResultSidebar();
    }
}
