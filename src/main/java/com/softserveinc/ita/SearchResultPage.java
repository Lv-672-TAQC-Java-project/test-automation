package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Product;
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

    //This method will be replaced
    @Step("Added product number {productIndex} to the comparison List")
    public SearchResultPage addProductToComparisonModal(int productIndex) {
        String productNumber = String.format("(//div[@class='goods-tile__inner'])[%s]", productIndex);
        String addToComparisonModalButton = "//*[@class ='compare-button ng-star-inserted']";
        String addProductToComparisonModal = productNumber + addToComparisonModalButton;
        $x(addProductToComparisonModal).click();
        return this;
    }

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
}
