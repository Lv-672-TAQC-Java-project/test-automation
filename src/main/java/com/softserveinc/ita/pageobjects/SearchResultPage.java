package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.FilterCategorySideBar;
import com.softserveinc.ita.pageobjects.components.Filter;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.models.SortOrder;
import com.softserveinc.ita.pageobjects.product.Product;
import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

@Getter
public class SearchResultPage {

    private final Header header = new Header();
    private final Filter filter = new Filter();
    private final FilterCategorySideBar filterCategorySideBar = new FilterCategorySideBar();

    public List<Product> getProducts() {
        List<Product> products = new LinkedList<>();
        String productsPath = "//div[@class='goods-tile__inner']";
        int amountOfProducts = $$x(productsPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(10)).size();

        for (int i = 1; i <= amountOfProducts; i++) {
            products.add(new Product(format("(%s)[%s]", productsPath, i)));
        }

        return products;
    }

    public Product getProduct(int index) {

        return new Product(format("(//div[@class='goods-tile__inner'])[%s]", index));
    }

    public Product getProductNotAddedToComparison(int index) {
        String productPath = "div[@class='goods-tile__inner']";

        return new Product(format("(//*[@class = 'compare-button ng-star-inserted'])[%s]//ancestor::%s", index, productPath));
    }

    @Step("added products to comparison")
    public Header addProductsToComparison(List<String> productsNames) {
        productsNames.forEach(productName -> getProduct(productName)
                .addToListOfComparisons());

        return new Header();
    }

    public Product getProduct(String name) {

        return new Product(format("//span[contains(text(),'%s')]/ancestor::div[@class='goods-tile__inner']", name));
    }

    public List<Integer> getProductsPrices(List<Product> products) {
        SearchResultPage searchResultPage = this;
        return rangeClosed(1, products.size())
                .mapToObj(product -> searchResultPage
                        .getProduct(product)
                        .getPrice())
                .collect(toList());
    }

    public String getSearchTermLabel() {
        return $x("//div[@class='search-header ng-star-inserted']/h1").getText();
    }

    @Step("Sorted products {order}")
    public SearchResultPage sort(SortOrder order) {
        $x("//select").selectOptionByValue(order.getSortOrderOption());

        return this;
    }
}
