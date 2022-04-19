package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.Getter;
import java.time.Duration;
import java.util.ArrayList;
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

    public String getSearchTermLabel() {
        return $x("//div[@class='search-header ng-star-inserted']/h1").getText();
    }

    @Step("Sorted products from cheap to expensive")
    public SearchResultPage sortProductsFromCheapToExpensive() {
        $x("//select").selectOptionByValue("1: cheap");

        return new SearchResultPage();
    }

    public List<Integer> getPricesOfProducts(List<Product> productsList) {
        List<Integer> productPricesList = new ArrayList<>();
        for (Product product : productsList) {
            productPricesList.add(product.getIntegerPrice());
        }

        return productPricesList;
    }

    public boolean isPricesOfProductsIncreasing(List<Integer> prices) {
        for (int i = 0; i <= prices.size() - 2; i++) {
            Integer firstPrice = prices.get(i);
            Integer secondPrice= prices.get(i + 1);

            if (firstPrice > secondPrice) {
                return false;
            }
        }
        return true;
    }
}
